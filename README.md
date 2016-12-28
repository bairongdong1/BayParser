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

this example do something like match_phrase query.
http://192.168.7.235:9200/test2/_search
```
{
    "query": {
        "bool":{
            "must":[
                {
                    "bay_query" :{
                        "text":"w(荧,光,剂)"
                     }
                }
            ]
        }
    }
}
```


this example means, "free,cost,hot" this is three word must be divided parts of your analyzer, the three words also should connect in row. 
ie."free cost heart hot" is invalid.
"good plan" should be in a row.

"free cost hot" should has near with "good plan", two words seperated most.
"free cost hot a fox good plan" is valid
"free cost hot a fox hotel good plan" is invalid
"free cost a hot hotel good plan" is invalid
```
{
    "query": {
        "bool":{
            "must":[
                {
                    "bay_query" :{
                        "text":"3w(w(free,cost,hot), w(good, plan))"
                     }
                }
            ]
        }
    }
}
```

the benchmark perform in ngram=1, 2(min 1, max 2) is not that bad , Chinese docs(80 words per doc maybe) could be handled in my mac book(SSD disk) with memory 300MB, query finished in 500ms(sometimes 100ms).

not implement escape characters or other special args now.

more info about query string:

https://lucidworks.com/blog/2009/02/22/exploring-query-parsers/

more info about surround query and span query.

https://lucidworks.com/blog/2009/07/18/the-spanquery/

ES issue about this kind parser.

https://github.com/elastic/elasticsearch/issues/11328
