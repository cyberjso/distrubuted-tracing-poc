#!/bin/bash

while [ true ]; do

  curl -w "\n" -X POST \
       --connect-timeout 30 \
       -H "Content-Type: application/json" \
       --data '{"name": "jack", "addressId": "1"}' \
       http://localhost:8080/person

  # This one is intentionally expected to take more time on address-api
  curl  -w "\n" -X POST \
         --connect-timeout 30 \
         -H "Content-Type: application/json" \
         --data '{"name": "jack", "addressId": "2"}' \
       http://localhost:8080/person

  curl -w "\n" -X POST \
         --connect-timeout 30 \
         -H "Content-Type: application/json" \
         --data '{"name": "jack", "addressId": "3"}' \
         http://localhost:8080/person

  # This one should not have any address associated with It
  curl -w "\n" -X POST \
         --connect-timeout 30 \
         -H "Content-Type: application/json" \
         --data '{"name": "jack", "addressId": "5"}' \
         http://localhost:8080/person

  # Forces a pause
  sleep 2

done
