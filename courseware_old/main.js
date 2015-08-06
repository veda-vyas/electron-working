var app = require('app');  // Module to control application life.
var BrowserWindow = require('browser-window');  // Module to create native browser window.
var ipc = require('ipc'); // Inter Process Communication

// Report crashes to our server.
require('crash-reporter').start();

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the javascript object is GCed.
var mainWindow = null;


// Quit when all windows are closed.
app.on('window-all-closed', function() {
  if (process.platform != 'darwin')
    app.quit();
});

/*
var mico = require('microphone');

mico.startCapture();

mico.audioStream.on('data', function(data) {
	process.stdout.write(data);
});*/


//update with your emailid

var userid="";
//Write to a file in local system
var myfs = require('fs');
myfs.exists(__dirname+"/userdatajson.json",function(exists){
	if(!exists){
		myfs.writeFile(__dirname+"/userdatajson.json",JSON.stringify({"userdata":{}}),"utf8",function(err){});
		console.log("File Created.");
	}
	else{
		console.log("File exists.");
	}
});
/*var myjson = { "userdata":[]};
console.log("myjson: "+myjson);
myfs.writeFile(__dirname+"/userdatajson.json", JSON.stringify( myjson ), "utf8", function (err) {
	console.log("Callback");
	if (err) throw err;
	console.log("File Saved");
});
*/
myJSON = myfs.readFile(__dirname+"/moduledata.json", flag="utf8", function(err, data){
	
	data = JSON.parse(data);
	var modjson=data;
	for(i=1; i<=Object.keys(modjson["modules"]).length; i++){
		console.log("module no: "+modjson["modules"][""+i]["title"]);
		for(var j=0;j<modjson["modules"][""+i]["lessons"].length;j++){
			modjson["modules"][""+i]["lessons"][j]["qid"]=i+"."+(j+1);
			
		}	
	}
	myfs.writeFile(__dirname+"/moduledata1.json",JSON.stringify(modjson),"utf8",function(err){});
});
//Check if internet connection is present through dns lookup


//encrypt & decrypt functions
var crypto = require('crypto'),
    algorithm = 'aes-256-ctr',
    password = 'd6F3Efeq';
 
function encrypt(text){
  var cipher = crypto.createCipher(algorithm,password)
  var crypted = cipher.update(text,'utf8','hex')
  crypted += cipher.final('hex');
  return crypted;
}
 
function decrypt(text){
  var decipher = crypto.createDecipher(algorithm,password)
  var dec = decipher.update(text,'hex','utf8')
  dec += decipher.final('utf8');
  return dec;
}
console.log(decrypt("2e8fc291"));
//end of encrypt & decrypt
// encrypt & decrypt example
// myJSON = myfs.readFile(__dirname+"/moduledata.json", flag="utf8", function(err, data){
// 	data = JSON.parse(data);
// 	var modjson=data;
// 	for(i=1; i<=Object.keys(modjson["modules"]).length; i++){
// 		//console.log("module no: "+modjson["modules"][""+i]["title"]);
// 		for(var j=0;j<modjson["modules"][""+i]["lessons"].length;j++){
// 			var type=modjson["modules"][""+i]["lessons"][j]["type"];
// 			if(type=="quiz"){
// 				var mdata=modjson["modules"][""+i]["lessons"][j]["answer"];
// 				for (var k=0;k<mdata.length;k++){
// 					if(mdata[k]!=""){
// 						console.log("answer data: "+mdata[k]+" qid: "+modjson["modules"][""+i]["lessons"][j]["qid"]);
//     					mdata[k]=encrypt(mdata[k]);
//     				}
//     			}
//     			modjson["modules"][""+i]["lessons"][j]["answer"]=mdata;
// 			}
			
// 		}	
// 	}
// 	myfs.writeFile(__dirname+"/moduledata_decrypt.json",JSON.stringify(modjson),"utf8",function(err){});
// });

/*myJSON = myfs.readFile(__dirname+"/sample_ency.json", flag="utf8", function(err, data){
	data=JSON.parse(data);
	var myjson=data;
	for (var k=0;k<data["modules"].length;k++){
		myjson["modules"][k]["ans"]=decrypt(data["modules"][k]["ans"]);
	}
	myfs.writeFile(__dirname+"/sample_decy.json",JSON.stringify(myjson),"utf8",function(err){});
});
*/
//end of the encrypt & decrypt example

// This method will be called when Electron has done everything
// initialization and ready for creating browser windows.
app.on('ready', function() {
  // Create the browser window.
  mainWindow = new BrowserWindow({width: 1366, height: 768, frame: true});
  //console.log("DIR NAME: "+__dirname);
  // and load the index.html of the app.
   //mainWindow.loadUrl('file://' + __dirname + '/course.html');
  mainWindow.loadUrl('file://' + __dirname + '/login.html');
  /*var myfs = require('fs');
  myfs.exists(__dirname+"/userdetails.json",function(exists){
    if(!exists) {
        mainWindow.loadUrl('file://' + __dirname + '/login.html');
    } else {
      //console.log("fileexists");
      mainWindow.loadUrl('file://' + __dirname + '/course.html');
    }
  });*/
  
  //mainWindow.loadUrl('file://' + __dirname + '/module.html?mod=1&les=4');
  //mainWindow.loadUrl('https://github.com');
  //mainWindow.loadUrl('https://google.com');
  //mainWindow.loadUrl('file://'+__dirname+'/keystest.html');
  
  // IPC to navigate to another URL
  ipc.on('asynchronous-message', function(event, arg) {
	 mainWindow.loadUrl('file://' + __dirname + arg);
	  checkOnlineAndUpdate();
  });
  ipc.on('details',function(event,data){
	  
	  var myfs = require('fs');
	  myfs.exists(__dirname+"/userdetails.json",function(exists){
	    if(!exists) {
	    	var details = data.split(" ");
	    	var userdetails = "{}";
	    	userdetails = JSON.parse(userdetails);
	    	userdetails["userid"] = details[0];
	    	userid = details[0];
	  		userdetails["pin"] = details[1];
	      	userdetails["lastseen_qid"] = "1.1";
	      	myfs.writeFile(__dirname+"/userdetails.json",JSON.stringify(userdetails),"utf8",function(err){});
	      	console.log("created");
	    } else {

	    	myJSON = myfs.readFile(__dirname+"/userdetails.json", flag="utf8", function(err, userdetails){
	    		var details = data.split(" ");
	    		userdetails = JSON.parse(userdetails);
	    		userdetails["userid"] = details[0];
	    		userid = details[0];
	  			userdetails["pin"] = details[1];
	    		myfs.writeFile(__dirname+"/userdetails.json",JSON.stringify(userdetails),"utf8",function(err){});
	    	});
	    	
	      	console.log("fileexists");

	    }
	    //getContent();
	    setTimeout(function(){
				checkOnlineAndUpdate();
				mainWindow.loadUrl('file://' + __dirname + '/course.html');
		}, 6000);
	    
		});
	  console.log(data);
});
  
  //IPC to store userdata
  ipc.on('process-data', function(event, arg,modno) {
	myJSON = myfs.readFile(__dirname+"/userdatajson.json", flag="utf8", function(err, data){
		//console.log(err);
		if (err) throw err;
		data = JSON.parse(data);
		var qid=arg["qid"];
		updatelastseenqid(qid);
		var record={"startTime":arg["startTime"],"submittedTime":arg["submittedTime"],"submittedAnswer":arg["submittedAnswer"],"score":arg["score"]};
		if(data["userdata"][modno]){
				if(data["userdata"][modno][qid]){
						var len=data["userdata"][modno][qid]["data"].length;
						data["userdata"][modno][qid]["data"][len]=record;

				}else{
					data["userdata"][modno][qid]={};
					data["userdata"][modno][qid]["data"]=[record];
					data["userdata"][modno][qid]["lastsync"]=0;
				}
		}
		else{
			data["userdata"][modno]={};
			data["userdata"][modno][qid]={};
			data["userdata"][modno][qid]["data"]=[record];
			data["userdata"][modno][qid]["lastsync"]=0;	
		}
		myfs.writeFile(__dirname+"/userdatajson.json",JSON.stringify(data),"utf8",function(err){});
	});
  });
  function updatelastseenqid(qid){
  	myJSON = myfs.readFile(__dirname+"/userdetails.json", flag="utf8", function(err, data){
  		data=JSON.parse(data);
  		data["lastseen_qid"]=qid;
  		myfs.writeFile(__dirname+"/userdetails.json",JSON.stringify(data),"utf8",function(err){});
  	});
  }

function getContent() {
 //Install NPM Package 'npm install request'
 var request = require('request');
 var fs = require('fs');
 request.post('https://msit-prep.appspot.com/coursejson.json')
        .pipe(fs.createWriteStream(__dirname+"/coursedata.json"));
 request.post('https://msit-prep.appspot.com/modulejson.json')
        .pipe(fs.createWriteStream(__dirname+"/moduledata.json"));
}
function updateProgramScore(mod,less,score){
	myJSON = myfs.readFile(__dirname+"/userdatajson.json", flag="utf8", function(err, data){
		//console.log(err);
		if (err) throw err;
		data = JSON.parse(data);
		var qid=mod+"."+less;
		console.log("qid:"+qid);
		if(data["userdata"][mod][qid]){
				var len=data["userdata"][mod][qid]["data"].length;
				data["userdata"][mod][qid]["data"][len-1]["score"] = score;

		}
		myfs.writeFile(__dirname+"/userdatajson.json",JSON.stringify(data),"utf8",function(err){});
	});
}
 function appendProgram(mod,less,program,output,score){
	myJSON = myfs.readFile(__dirname+"/moduledata.json", flag="utf8", function(err, data){
	data = JSON.parse(data);
	var myjson=data;
	var outputwithbr="";
	for(var i=0;i<output.length;i++){
		if(output.charAt(i)=='\n'){
			outputwithbr +="<br>";
		}else{
			outputwithbr +=output.charAt(i);
		}
	}
	//myjson["modules"][mod]["lessons"][parseInt(less)-1]["question"]=program;
	myjson["modules"][mod]["lessons"][parseInt(less)-1]['output']=outputwithbr;
	//console.log("qn: "+myjson["modules"][mod]["lessons"][parseInt(less)-1]["output"]);
	myfs.writeFile(__dirname+"/moduledata.json",JSON.stringify(myjson),"utf8",function(err){});
});
updateProgramScore(mod,less,score);
console.log("Score:"+score);
//console.log(mod+" "+less+" "+program+" "+output);
//mainWindow.loadUrl('file://' + __dirname + "module.html?mod="+mod+"&les="+(parseInt(less)));
}
ipc.on('execute-program',function(event,classname,expectedoutput,arg,modles){
  	console.log("retrieved dat:"+expectedoutput);
  	var moduleandlesson=modles.split("|");
  	var mod=moduleandlesson[0];
  	var lesson=moduleandlesson[1];
  	console.log("mod:"+mod+" lesson:"+lesson);
  	var writeStream = myfs.createWriteStream(__dirname+"/programs/"+classname+".java");
	writeStream.write(arg);
	//writeStream.write("Thank You.");
	writeStream.end();
	var output="";
	var nodeexec = require('child_process').exec;
	nodeexec('javac '+__dirname+'\\programs\\'+classname+'.java', function callback(error, stdout, stderr){
		if(error == null){
			nodeexec('java -cp '+__dirname+'\\programs '+classname, function callback(error, stdout, stderr){
				if(error == null){
					output=stdout;
					var score="0";
					if(output.match(expectedoutput)){
						score = "1";
						output=stdout+"<br><br> Testcase Passed : true"
					}else{
						score = "0";
						output=stdout+"<br><br> Testcase Passed : false"
					}
					console.log("NODE EXEC OUTPUT: "+stdout+" output: "+output);
					appendProgram(mod,lesson,arg,output,score);
				} else {
					output=stderr;
					console.log("NODE EXEC4: "+stderr);
					appendProgram(mod,lesson,arg,output,"0");
					
					//console.log("NODE EXEC4: "+stdout);
					//console.log("NODE EXEC4: "+stderr);
				}
			});
		} else {
			
			output=stderr;
			console.log("NODE EXEC: "+stderr);
			appendProgram(mod,lesson,arg,output,"0");
			
			//console.log("NODE EXEC: "+stdout);
			//console.log("NODE EXEC: "+stderr);
		}
	});
});
ipc.on('execute-Graphicprogram',function(event,classname,testcases,arg,modles){
  	console.log("retrieved data:"+arg);
  	var moduleandlesson=modles.split("|");
  	var mod=moduleandlesson[0];
  	var lesson=moduleandlesson[1];
  	console.log("mod:"+mod+" lesson:"+lesson);
  	var writeStream = myfs.createWriteStream(__dirname+"/programs/"+classname+"/"+classname+".java");
	writeStream.write(arg);
	writeStream.write(testcases);
	//writeStream.write("Thank You.");
	writeStream.end();
	var output="";
	var nodeexec = require('child_process').exec;
	nodeexec('javac '+__dirname+'\\programs\\'+classname+'\\*.java', function callback(error, stdout, stderr){
		if(error == null){
			console.log('java -cp '+__dirname+'\\programs\\'+classname+'\\'+classname);
			nodeexec('java -cp '+__dirname+'\\programs\\'+classname+' '+classname, function callback(error, stdout, stderr){
				if(error == null){
					output=stdout;
					var score="0";
					if(output=="Testcase Passed: true\r\n"){
						score = "1";
					}
					else{
						score = "0";
					}
					console.log("NODE EXEC OUTPUT: "+stdout+" output: "+output);
					appendProgram(mod,lesson,arg,output,score);
				} else {
					output=stderr;
					console.log("NODE EXEC4: "+stderr);
					console.log("score: 0");
					appendProgram(mod,lesson,arg,output,"0");
					
					//console.log("NODE EXEC4: "+stdout);
					//console.log("NODE EXEC4: "+stderr);
				}
			});
		} else {
			output=stderr;
			console.log("NODE EXEC: "+stderr);
			console.log("score: 0");
			appendProgram(mod,lesson,arg,output,"0");
			
			//console.log("NODE EXEC: "+stdout);
			//console.log("NODE EXEC: "+stderr);
		}
	});
});
ipc.on('execute-Stdinprogram',function(event,classname,expectedoutput,input,userprogram,modles){
	
	console.log("retrieved data:"+userprogram);
	var moduleandlesson=modles.split("|");
 	var mod=moduleandlesson[0];
 	var lesson=moduleandlesson[1];
 	console.log("mod:"+mod+" lesson:"+lesson);
 	var writeStream = myfs.createWriteStream(__dirname+"/programs/"+classname+".java");
  	writeStream.write(userprogram);
	writeStream.end();

	var output="";
	var child_process = require('child_process');
	var fs = require('fs');
	var nodeexec = require('child_process').exec;
	console.log("=========================="+__dirname);
	nodeexec('javac '+__dirname+'\\programs\\'+classname+'.java', function callback(error, stdout, stderr){
		if(error == null){
			var myfs = require('fs');
			program = child_process.exec('java -cp '+__dirname+'\\programs '+classname, function callback(error,stdout,stderr){
				output=stdout;
				var score="0";
				if(output.match(expectedoutput)){
					score = "1";
					output=stdout+"<br><br> Testcase Passed : true"
					console.log(output);
				}else{
					score = "0";
					output=stdout+"<br><br> Testcase Passed : false"
				}
				appendProgram(mod,lesson,userprogram,output,score);
			});
			program.stdin.end(input);
			
		}else{
			console.log(error);
			output=stderr;
			console.log("NODE EXEC4: "+stderr);
			appendProgram(mod,lesson,userprogram,output,"0");
		}
	});
});
ipc.on('execute-Apiprogram',function(event,classname,testcases,userprogram,modles){
  	console.log("retrieved data:"+userprogram+"\n"+testcases);
  	var moduleandlesson=modles.split("|");
  	var mod=moduleandlesson[0];
  	var lesson=moduleandlesson[1];
  	console.log("mod:"+mod+" lesson:"+lesson);
  	var writeStream = myfs.createWriteStream(__dirname+"/programs/"+classname+".java");
	writeStream.write(userprogram+"\n");
	writeStream.write(testcases);
	writeStream.end();

	var output="";
	var userprogram_error_status=false;
	var nodeexec = require('child_process').exec;
	nodeexec('javac '+__dirname+'\\programs\\'+classname+'.java', function callback(error, stdout, stderr){
		if(error == null){
			nodeexec('java -cp '+__dirname+'\\programs '+classname, function callback(error, stdout, stderr){
				if(error == null){
					output=stdout;
					var key="Score: ";
					var index = output.search("Score: ");
					var endpos = index+key.length+1;
					var score=output.substring(index+key.length,endpos);

					console.log("NODE EXEC OUTPUT TestPrgm: "+stdout+" output: "+output+" score: "+score);
					appendProgram(mod,lesson,userprogram,output,score);
				} else {
					output=stderr;
					console.log("NODE EXEC4: "+stderr);
					appendProgram(mod,lesson,userprogram,output,"0");
					
					//console.log("NODE EXEC4: "+stdout);
					//console.log("NODE EXEC4: "+stderr);
				}
			});
		} else {
			output=stderr;
			console.log("NODE EXEC: "+stderr);
			appendProgram(mod,lesson,userprogram,output,"0");
		}
	});
	
});


function loadUserdata(){
	var userdata="";
	myJSON = myfs.readFile(__dirname+"/userdatajson.json", flag="utf8", function(err, data){
		
		console.log("userid: "+userid);
		var syncdata=JSON.parse('{"userdata":{}}');
		data = JSON.parse(data);
		var jsondata=data;
		Object.keys(data["userdata"]).forEach(function(key){
			Object.keys(data["userdata"][key]).forEach(function(qid){
				var lastsync=data["userdata"][key][qid]["lastsync"];
				var j=0;
				if(!syncdata["userdata"][key]){
					syncdata["userdata"][key]={};
					syncdata["userdata"][key][qid]=[];
				}
				else if(!syncdata["userdata"][key][qid]){
					syncdata["userdata"][key][qid]=[];
				}
				
				for(var i=lastsync;i<data["userdata"][key][qid]["data"].length;i++){
					syncdata["userdata"][key][qid][j]=data["userdata"][key][qid]["data"][i];
					j++;
				}
				
			});
		});
			
			userdata=JSON.stringify(syncdata);
			//console.log("records: "+userdata);
			var httpify = require('httpify');

			var req = httpify({
			//url: 'https://activity-log-tmt.appspot.com/getdigistate',
			url: 'https://msit-prep.appspot.com/setcourse',
			method: 'POST',
			type: 'text',
			
			form: {userid: userid, userdata: userdata, course : "java"}
			}, function (err, response, body) {
				console.log("sync error in form: "+err);
			});

			req.then(function (response) {
				var msg=response.body;
				if(msg=="Success"){
					//console.log("inside if "+msg);
					Object.keys(syncdata["userdata"]).forEach(function(key){
						Object.keys(syncdata["userdata"][key]).forEach(function(qid){
							
							var prevsync=jsondata["userdata"][key][qid]["lastsync"];
							var syncno=prevsync+syncdata["userdata"][key][qid].length;
							//console.log(qid+" inside object keys: "+syncno);
							jsondata["userdata"][key][qid]["lastsync"]=syncno;
						});
					});
					myfs.writeFile(__dirname+"/userdatajson.json",JSON.stringify(jsondata),"utf8",function(err){});
					//console.log("updated......");
				}
				else{
					console.log("not updated..."+msg);
					
				}
			}, function (errResponse) {
			// status: 400 - 599 
				console.log("sync err: "+errResponse);
			});
		
	});
	
}
function checkOnlineAndUpdate(){
	var isOnline = require('is-online');
	isOnline(function(err, online) {
	console.log("Online Status: "+online);
		if(online){
			loadUserdata();
		}
	});
}
/*var nodeexec = require('child_process').exec;
nodeexec('hostname', function callback(error, stdout, stderr){
	userid=stdout.trim();
	console.log("hostname: "+stdout);
	//syncdata();
});*/

  // Emitted when the window is closed.
  mainWindow.on('closed', function() {
    // Dereference the window object, usually you would store windows
    // in an array if your app supports multi windows, this is the time
    // when you should delete the corresponding element.
    mainWindow = null;
  });
});