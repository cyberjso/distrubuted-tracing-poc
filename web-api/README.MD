
# Distributed Tracing PoC


## How to run this example

### Traces on Jaeger

1. Build application container images
<pre>docker-compose -f docker-compose-jager.yaml build</pre>
2. Spin up the entire stack
<pre>docker-compose -f docker-compose-jager.yaml up -d</pre>
3. Rum the script to simulate requests to the APIs
<pre>./requests.sh</pre>
4. Open your browser and access [Jaeger UI](http://localhost:16686/). There you will be able to see and filter the traces.

### Traces on  Grafana Tempo

1. (Optional) Tear down the infrastructure you spinned up on [here](#traces-on-jaeger)
<pre>docker-compose -f docker-compose-jager.yaml down -v</pre>
2. (Optional) Build container imagers only if you did not yet run  this step 1 from [this section](#traces-on--grafana-tempo)
<pre>docker-compose -f docker-compose-tempo.yaml build</pre>
3. Spin up the entire stack
<pre>docker-compose -f docker-compose-tempo.yaml up -d</pre>
4. Rum the script to simulate requests to the APIs
<pre>./requests.sh</pre>
5. Open your browser and access [Grafana UI](http://localhost:3000/).
   1. On the left menu, go to <i><b>Explore</b></i>
   2. On Query top section access <i><b>Service Graph</b></i>. There will be to filter the traces.
