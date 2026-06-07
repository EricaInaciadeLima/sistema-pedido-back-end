#!/bin/bash

# CONFIGURAÇÕES
BUCKET="meu-bucket"
PASTA_IMAGENS="./imagens"
ENDPOINT="http://127.0.0.1:4566"

# Verifica se a pasta existe
if [ ! -d "$PASTA_IMAGENS" ]; then
  echo "Pasta não encontrada: $PASTA_IMAGENS"
  exit 1
fi

echo "Iniciando upload das imagens..."

# Faz upload de jpg, jpeg, png, gif e webp
for arquivo in "$PASTA_IMAGENS"/*.{jpg,jpeg,png,gif,webp}; do

  # ignora quando não existir arquivo
  [ -e "$arquivo" ] || continue

  nome=$(basename "$arquivo")

  echo "Enviando: $nome"

  aws --endpoint-url=$ENDPOINT s3 cp "$arquivo" "s3://$BUCKET/$nome"

done

echo "Upload concluído!"