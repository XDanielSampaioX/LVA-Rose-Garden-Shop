import os
import time
import requests
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options

# Caminho para o seu driver do Chrome
chrome_driver_path = r"C:\\Program Files\\LVA-Rose-Garden-Shop\\back\\src\\main\\resources\\chromedriver.exe"  # Altere para o local do seu ChromeDriver

# URL da página onde as imagens estão localizadas
url = "https://rosadodesertofortaleza.com.br/product-category/avarias/"

# Crie uma pasta para armazenar as imagens
os.makedirs("imagens_baixadas", exist_ok=True)

# Configuração do Selenium para não abrir uma janela do navegador
chrome_options = Options()
chrome_options.add_argument("--headless")  # Roda em segundo plano
chrome_options.add_argument("--disable-gpu")

# Usando o Service para configurar o driver
service = Service(chrome_driver_path)

# Iniciar o driver do Selenium com o Service
driver = webdriver.Chrome(service=service, options=chrome_options)

# Acessar a página
driver.get(url)

# Atraso para garantir que a página carregue
time.sleep(5)

# Encontrar todas as imagens na página
images = driver.find_elements(By.TAG_NAME, "img")

# Iniciar o processo de download para cada imagem
for idx, img in enumerate(images):
    # Obtenha o link da imagem
    img_url = img.get_attribute("src")
    
    if img_url:
        # Buscar o <a> relacionado à imagem
        parent_a_tag = img.find_element(By.XPATH, "..")  # Obtém o elemento <a> que é o pai da imagem
        product_name = parent_a_tag.get_attribute("aria-label")  # Pegamos o nome diretamente do aria-label, se existir
        
        # Caso não tenha aria-label, tentar pegar o texto dentro do <a>
        if not product_name:
            product_name = parent_a_tag.text.strip()

        # Se ainda não houver nome, use um nome padrão baseado no índice
        if not product_name:
            product_name = f"imagem_{idx + 1}"

        # Substituir espaços por underscores e garantir que o nome seja seguro para arquivos
        flower_name = f"{product_name}.jpg"
        flower_name = flower_name.replace(" ", "_").replace("/", "_").replace("–", "-")

        # Usando a biblioteca 'requests' para fazer o download da imagem
        try:
            response = requests.get(img_url, stream=True)
            response.raise_for_status()  # Garantir que não houve erro na requisição

            # Salvar a imagem com o nome obtido
            with open(os.path.join("imagens_baixadas", flower_name), "wb") as f:
                for chunk in response.iter_content(1024):
                    f.write(chunk)

            print(f"Imagem '{flower_name}' baixada com sucesso!")

        except requests.exceptions.RequestException as e:
            print(f"Erro ao baixar a imagem {flower_name}: {e}")

        # Atraso de 1 segundo entre cada requisição para simular o comportamento humano
        # time.sleep(1)

# Fechar o driver
driver.quit()

print("Processo finalizado.")
