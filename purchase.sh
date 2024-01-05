#!/bin/bash
curl --request POST --url 'localhost:28852/purchase' --header 'content-type: application/json' --data "{\"row\":$1,\"column\":$2}"
echo ""
