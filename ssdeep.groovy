import info.debatty.java.spamsum.*;
def scorer = new SpamSum();
int  scoreMaxx = 0;
int scoreCurr = 0;

for(int i=0; i<doc[field].size(); i++){
    scoreCurr = scorer.match(doc[field][i], term);
    if(scoreCurr > scoreMaxx){
        scoreMaxx = scoreCurr;
    }
}
return scoreMaxx;
 curl -X GET "localhost:9200/_search?pretty" -H 'Content-Type: application/json' -d'
{
  "query": {
    "function_score": {
      "query": {
        "exists": {
          "field": "recon.context_hash"
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
                   "term" : "48:rj1f61/gMqYyxco7jNhmjMn1Op0jhUFp51DIUWn1YYU2:ff61/RIcqjWwn0pYUFpJNu"
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

