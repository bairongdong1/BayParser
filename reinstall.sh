mvn clean package
cp src/main/resources/plugin-descriptor.properties target
zip target/BayParser-0.0.1.zip target/BayParser-0.0.1.jar target/plugin-descriptor.properties
/usr/local/Cellar/elasticsearch/2.1.1/libexec/bin/plugin remove head file:/Users/baidong/lab/bay_plugin/target/BayParser-0.0.1.zip
/usr/local/Cellar/elasticsearch/2.1.1/libexec/bin/plugin install file:/Users/baidong/lab/bay_plugin/target/BayParser-0.0.1.zip
