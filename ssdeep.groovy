import info.debatty.java.spamsum.*;
def scorer = new SpamSum();
int scoreMaxx = 0;
int scoreCurr = 0;
String fieldNew = field.endsWith(".keyword")?field:field+".keyword";
for(int i=0; i<doc[fieldNew].size(); i++){
    scoreCurr = scorer.match(doc[fieldNew][i], term);
    if(scoreCurr > scoreMaxx){
        scoreMaxx = scoreCurr;
    }
}
return scoreMaxx;
