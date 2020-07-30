import Component from '@glimmer/component';
import { action } from '@ember/object';
import { tracked } from '@glimmer/tracking';
import jQuery from 'jquery'; 

export default class Jni1 extends Component {
    @tracked folder_path;
    @tracked pattern;
    @tracked treePath;
    @tracked items;
    items = [
      { name: "foo1" },
      { name: "foo2" },
      { name: "foo3", item: [
          { name: "foo4" },
          { name: "foo5" },
          { name: "foo6", item: [
              { name: "foo7" }    
          ]}
      ]},
      { name: "foo8" }
  ];
    @action
    
    folderSearch(event){
      event.preventDefault();
      //this.tree = "response";
      if(this.folder_path == "" || this.pattern == "")
      {
          alert("Please add all fields")
          return;
      }
      if(/^([A-Za-z]:)(\\[A-Za-z_\-0-9\.\s]*)*$/.test(this.folder_path))
      {
        console.log("Successs...")
        console.log(this.folder_path + " " + this.pattern);
        
        jQuery.ajax({
          url: "http://localhost:8080/JniEmber/folderSearch",
          type: "POST",
          data: {
            "folderPath": this.folder_path,
            "pattern": this.pattern
        }
      }).then((response) =>{

         //alert(response.length)
         alert(response)
        // this.folder_path = response
          if( response == null )
          {
            document.getElementById("q").innerHTML = "<br>No matching found";
            return
          }
          if(response == "Enter valid path")
          {
            document.getElementById("q").innerHTML = "<br>Enter valid path";
            return;
          }
          
            console.log( response );
            var reslength = response.length
            var ind = 0 
            response.every((value)=> { 
              alert(value)
              ind++
              return (ind < reslength/2); 
            })
            
            
            var folderTree = ""
            var tree = this.folder_path.split("\\")       //  folder tree creation part
            this.treePath = this.folder_path.split("\\") 
            tree.forEach(folderName =>{
              if(folderName !="") // E://
              {
                if(tree[0] == folderName)
                  folderTree += "<ul id='myUL'>" + "<li>"+ "<span class='arrow'>"+ folderName + "</span>";
                else
                  folderTree += "<ul class='nested'>" + "<li>"+ "<span class='arrow'>"+ folderName + "</span>";
           
              }
            })
            
            folderTree += "<ul class='nested'>";

            ind = 0
            var fileNameind = 0 
            response.forEach(element => {
              if(ind >= reslength/2) 
              {

                folderTree += "<li><span class='arrow'>" + response[fileNameind++] + "</span>" ;
                folderTree += "<ul class='nested'>" + "<li class='details'>"+ element +"<br></li></ul>";

              }
              ind++
            })

            response.forEach(element => {
              if(ind <= reslength)
                folderTree += "</li>";
            
            })
            ind = 0
            folderTree +="</ul>";
            response.forEach(folderName =>{
              if(ind < reslength/2)
                folderTree += "</li>"+"</ul>";
            })

            console.log(folderTree)
            document.getElementById("q").innerHTML = "<br>" + folderTree;

            
/////////////////////////////////////////////////////////////////////////////


          var transform = document.getElementsByClassName("arrow");
          var i;
          
          for (i = 0; i < transform.length; i++) {
              transform[i].addEventListener("click", function() {
              this.parentElement.querySelector(".nested").classList.toggle("active");
              this.classList.toggle("arrow-down");
            });
          }
          
          
      })
      .catch(err => {
        document.getElementById("q").innerHTML = "<br>Not connected to server!..."
        console.log("Errr.." + JSON.stringify(err))
      })

      //.catch(err => console.log("Errr.." + JSON.stringify(err)))
      return
      }
    
      else{
        alert("Enter valid path!...")
      }
      
      }  
}