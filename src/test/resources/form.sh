#!/bin/bash

curl -X POST -H 'Content-Type: application/json' --data '{"generation":8,"grid":[[false,false,false,false,false],[false,false,true,false,false],[false,false,true,false,false],[false,false,true,false,false],[false,false,false,false,false]]}' 'http://localhost:80/v1.0/world/next'
echo ""
