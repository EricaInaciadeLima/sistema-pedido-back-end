#!/bin/bash

BUCKET="meu-bucket"
PASTA_IMAGENS="./imagens"
ENDPOINT="http://127.0.0.1:4566"

echo "Verificando bucket..."

aws --endpoint-url=$ENDPOINT s3 ls s3://$BUCKET >/dev/null 2>&1

if [ $? -ne 0 ]; then
  echo "Bucket não existe. Criando..."
  aws --endpoint-url=$ENDPOINT s3 mb s3://$BUCKET
fi

if [ ! -d "$PASTA_IMAGENS" ]; then
  echo "Pasta não encontrada: $PASTA_IMAGENS"
  exit 1
fi

echo "Iniciando upload das imagens..."

for arquivo in "$PASTA_IMAGENS"/*.{jpg,jpeg,png,gif,webp}; do

  [ -e "$arquivo" ] || continue

  nome=$(basename "$arquivo")

  echo "Enviando: $nome"

  aws --endpoint-url=$ENDPOINT s3 cp "$arquivo" "s3://$BUCKET/$nome"

done

echo "Upload concluído!"