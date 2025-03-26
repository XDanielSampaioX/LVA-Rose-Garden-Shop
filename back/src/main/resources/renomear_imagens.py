import os
import hashlib

def calcular_hash(arquivo):
    """ Calcula o hash SHA256 do arquivo para identificar duplicatas """
    hasher = hashlib.sha256()
    with open(arquivo, 'rb') as f:
        while chunk := f.read(8192):
            hasher.update(chunk)
    return hasher.hexdigest()

def renomear_e_excluir_duplicatas(pasta, trecho_remover):
    if not os.path.exists(pasta):
        print(f"❌ ERRO: A pasta '{pasta}' não existe.")
        return
    
    print(f"📂 Pasta encontrada: {pasta}")
    
    arquivos = os.listdir(pasta)
    
    if not arquivos:
        print("⚠️ Nenhum arquivo encontrado na pasta.")
        return
    
    print(f"📄 Arquivos encontrados: {arquivos}")

    hash_dict = {}  # Armazena hashes dos arquivos
    arquivos_renomeados = set()  # Evita renomeação duplicada

    for arquivo in arquivos:
        caminho_antigo = os.path.join(pasta, arquivo)

        if not os.path.isfile(caminho_antigo):
            print(f"📁 Ignorado (não é um arquivo): {arquivo}")
            continue
        
        if trecho_remover not in arquivo:
            print(f"🟡 Ignorado (não contém '{trecho_remover}'): {arquivo}")
            continue

        print(f"✏️ Renomeando arquivo: {arquivo}")

        novo_nome = arquivo.replace(trecho_remover, "").strip()
        caminho_novo = os.path.join(pasta, novo_nome)

        # Verifica se já existe um arquivo com o novo nome
        if os.path.exists(caminho_novo):
            print(f"🚫 Arquivo com nome final já existe: {novo_nome}, excluindo {arquivo}...")
            os.remove(caminho_antigo)
            continue

        # Verifica duplicatas pelo conteúdo (hash)
        arquivo_hash = calcular_hash(caminho_antigo)

        if arquivo_hash in hash_dict:
            print(f"⚠️ Arquivo duplicado pelo conteúdo: {arquivo}, excluindo...")
            os.remove(caminho_antigo)
        else:
            hash_dict[arquivo_hash] = caminho_novo
            arquivos_renomeados.add(novo_nome)
            os.rename(caminho_antigo, caminho_novo)
            print(f"✅ Renomeado: {arquivo} -> {novo_nome}")

# 🔹 Configurar a pasta e o trecho a ser removido
pasta_destino = r"C:\Program Files\LVA-Rose-Garden-Shop\back\src\main\resources\imagens"
trecho_para_remover = "Rosa_do_deserto_"

renomear_e_excluir_duplicatas(pasta_destino, trecho_para_remover)
