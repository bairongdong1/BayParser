test ES parser for ES 2.1.1 (can't ensure it would work in ES 1.x or 2.2.x or upper)
just to implement Lucene Surround Query Parser.

basic way to do the thing like reinstall.sh.
take care of the path.

also, if your ES version is not 2.1.1 or jave version is different, 
change the thing in pom.xml.

also change the thing in src/main/resources/plugin-descriptor.properties.

java version must be something like 1.7.0 not 1.7.0_111.

I would try to do this install more normally in the future.
 


And the test query could be this, text is the target field(one field named text)

http://192.168.7.235:9200/test2/_search
```
{
    "query": {
        "bool":{
            "must":[
                {
                    "bay_query" :{
                        "text":"3w(荧,光,剂)"
                     }
                }
            ]
        }
    }
}
```

not implement escape characters or other special args now.

more info about query string:

https://lucidworks.com/blog/2009/02/22/exploring-query-parsers/

more info about surround query and span query.

https://lucidworks.com/blog/2009/07/18/the-spanquery/

ES issue about this kind parser.

https://github.com/elastic/elasticsearch/issues/11328
