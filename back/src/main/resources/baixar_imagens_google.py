import os
import time
import requests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys

# Caminho para o seu driver do Chrome
chrome_driver_path = r"C:\\Program Files\\LVA-Rose-Garden-Shop\\back\\src\\main\\resources\\chromedriver.exe"

# Termo de pesquisa no Google Imagens
search_query = "rosa do deserto enxertada"
url = f"https://www.google.com/search?tbm=isch&q={search_query}"

# Criar pasta para salvar imagens
os.makedirs("imagens_baixadas", exist_ok=True)

# Configuração do Selenium para não abrir uma janela do navegador
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--disable-gpu")

# Configurar o driver
service = Service(chrome_driver_path)
driver = webdriver.Chrome(service=service, options=chrome_options)

driver.get(url)
time.sleep(3)  # Tempo para carregar a página

# Rolar a página para carregar mais imagens
driver.find_element(By.TAG_NAME, "body").send_keys(Keys.END)
time.sleep(2)

# Encontrar todas as imagens na página
images = driver.find_elements(By.TAG_NAME, "img")

# Procurar o nome conforme a tag <div class="toI8Rb OSrXXb"> para cada imagem
for idx, img in enumerate(images):
    img_url = img.get_attribute("src")
    
    if img_url and "http" in img_url:
        try:
            # Ajustar para pegar o nome de uma tag mais próxima da imagem
            # Buscar por algum elemento mais próximo à imagem para obter o nome
            product_name_elements = img.find_elements(By.XPATH, ".//ancestor::div[contains(@class, 'toI8Rb')]")
            
            # Se não encontrar, usar um nome genérico
            if product_name_elements:
                product_name = product_name_elements[0].text.strip()
            else:
                product_name = f"rosa_deserto_{idx + 1}"  # Caso não encontre, usar um nome genérico
            
            # Limpar o nome para ser adequado como nome de arquivo
            product_name = product_name.replace(" ", "_").replace("/", "_").replace("–", "-")
            
            image_name = f"{product_name}.jpg"
            
            # Baixar a imagem
            response = requests.get(img_url, stream=True)
            response.raise_for_status()
            
            # Salvar a imagem com o nome adequado
            with open(os.path.join("imagens_baixadas", image_name), "wb") as f:
                for chunk in response.iter_content(1024):
                    f.write(chunk)
            
            print(f"Imagem '{image_name}' baixada com sucesso!")
        
        except requests.exceptions.RequestException as e:
            print(f"Erro ao baixar {image_name}: {e}")
        except Exception as e:
            print(f"Erro ao capturar o nome ou processar a imagem: {e}")

# Fechar o navegador
driver.quit()
print("Processo finalizado.")
