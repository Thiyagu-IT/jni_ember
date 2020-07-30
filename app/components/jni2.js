//import fs from 'npm:file-system';
import Component from '@glimmer/component';
import { action } from '@ember/object';
import { tracked } from '@glimmer/tracking';


export default class Jni2 extends Component {
  @tracked file_name;
  @tracked file_path;
  @tracked keyword;
  @tracked end_line;
  @tracked content ;
  @tracked result;
  @tracked escape = 1;

  @action

  
  @action
  autoComplete(){
    
    if(this.escape == 1)
    {
      this.escape++
      $.ajax({
        url     : "http://localhost:8080/JniEmber/getkeyword",
        method     : 'GET'
      })
      .then((resultText)=>{

          alert("Get keyword Success")
          alert("!! : "+ resultText);
          this.result = resultText;
          
        })
        .catch(err=> console.log("Error.. "+ err))
    }
  }
   

  @action
  keywordSearch(event){
        event.preventDefault();
        if(this.file_name == "" || this.file_path == "" || this.keyword == "" ||this.end_line == "")
        {
            alert("Please add all fields")
            return;
        }
        if(/^([A-Za-z]:)(\\[A-Za-z_\-0-9\.\s]*)*$/.test(this.file_path))
        {
          console.log("Successs...")
          console.log(this.file_name + " " + this.file_path);
          var sendDate = (new Date()).getTime();
          jQuery.ajax({
            url: "http://localhost:8080/JniEmber/keywordSearch",
            type: "POST",
            data: {
              "file_name": this.file_name,
              "file_path": this.file_path,
              "keyword": this.keyword,
              "end_line": this.end_line

          }
        })
        .then((response) =>{
          this.escape = 1;
          var receiveDate = (new Date()).getTime();

          var responseTimeMs = receiveDate - sendDate;
          console.log(responseTimeMs/1000 + " seconds")
            
          
            var responseLength = response.length -1;

            $.ajax({
              url     : "http://localhost:8080/JniEmber/addkeyword",
              method     : 'POST',
              data     : {
                name : this.keyword,
                time : responseTimeMs,
                length : responseLength,
                endline: this.end_line
              },
              success    : function(resultText){
              alert("Add keyword success : "+resultText)
              },
              error : function(jqXHR, exception){
              console.log('Error occured!!');
              }
              });

            if(response == "No match found")
            {
              document.getElementById("q").innerHTML = "<br>No match found";
              return
            }

            var finalResponse = "<br>"+"<table>";
            var replaceWord = "<span>"+ this.keyword + "</span>";
            var result, ind = 0
            response.forEach(element => {
              
                result = element.replace(this.keyword,replaceWord);
                response[ind++] = result

            });

            this.content = response
          
          })
        .catch(err => {
          document.getElementById("q").innerHTML = "<br>Not connected to server!..."
          console.log("Errr.." + JSON.stringify(err))
        })
        return;
        }
        
        alert("Please add all fields")
        }
        
         
}
