#!/bin/bash
curl --request POST --url 'localhost:28852/return' --header 'content-type: application/json' --data "{\"token\":\"$1\"}"
echo ""

