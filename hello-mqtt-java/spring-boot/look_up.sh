#!/bin/bash

for i in {1..100}
do
curl http://localhost:8080/temps/stats
echo
sleep 2
done

