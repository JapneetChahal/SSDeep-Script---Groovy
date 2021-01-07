### SSDeep Script - Groovy
Generates score on basis of string similarity. 

**To use the Script:-**
1. Copy the jar files( *spamsum.jar* & *string-similarity.jar*) in /usr/share/elasticsearch/modules/lang-groovy
2. Give the following permissions in plugin-security.policy in lang-groovy folder:
   - permission java.io.FilePermission "ALL FILES", "read"; 
   - permission java.io.FilePermission "/home/japneet/java-spamsum-0.2.jar", "read";
   - permission java.io.FilePermission "ALL FILES", "execute";
   - permission org.elasticsearch.script.ClassPermission "*";
3. Save the script in /etc/elasticsearch/scripts/
4. Restart ElastiSearch.

**Query to Generate Scores**
```
curl -X GET "localhost:9200/logvehere-probe-*/_search?pretty" -H 'Content-Type: application/json' -d'
{
  "query": {
    "function_score": {
      "query": {
        "exists": {
          "field": "recon.context_hash.keyword"
        }
      },
      "min_score" : 0,
        "functions": [
          {
            "script_score": {
              "script": {
                "file": "ssdeep",
                "lang": "groovy",
                 "params":{
                   "field" : "recon.context_hash.keyword",
                   "term" : "192:D1tNwAuO6Ak7ZYrlLHe3l9AmW9NIpycqAJDOgENi2QVGm8S6LQawRUenI7u:1f6AktElCom+mRqKG"
              }
            }
          }
        }
      ]
    }
  },
  "_source": ["recon.context_hash"]
}
' 



```
