
# Distributed Tracing PoC


## Required Setup
1. docker
2. docker-compose
3. No process running on the following ports
   1. 8080
   2. 8081
   3. 3000
   4. 5432
   5. 4317
   6. 4318
   7. 14260
   8. 8888
   9. 14268
   10. 3200
   11. 9411



## Running the PoC

1. Build application container images
<pre>docker-compose  build</pre>
2. Spin up the entire stack
<pre>docker-compose -d</pre>
3. Rum the script to simulate requests to the APIs
<pre>./requests.sh</pre>
4. Open your browser and access [Grafana UI](http://localhost:3000/).
   1. On the left menu, go to <i><b>Explore</b></i>
   2. On Query top section access <i><b>Service Graph</b></i>. There will be to filter the traces.