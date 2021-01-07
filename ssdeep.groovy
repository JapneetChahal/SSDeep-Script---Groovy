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
