#include <jni.h>      
#include <iostream> 
#include"UserInput.h"
#include <fstream>  
#include <cstring>

using namespace std;

void link_with_JavaCode(string line, JNIEnv *env)
{
	
	jclass output_class = env->FindClass("UserOutput");
	
	
	try
	{
		jmethodID id = env->GetMethodID(output_class,"<init>", "()V");
		
		jobject myobj = env->NewObject(output_class, id);
		
		jmethodID show = env->GetMethodID(output_class, "generateOutput", "(Ljava/lang/String;)V");
		
		jstring jStringParam = env->NewStringUTF(line.c_str());
		

		env->CallVoidMethod(myobj, show, jStringParam);
		
	}
	catch(...)
	{
		cout<<"Error ..."<<endl;
	}
	
}

JNIEXPORT void JNICALL Java_UserInput_searchString(JNIEnv *env, jobject thisObj,jstring file_name, jstring file_path, jstring endd, jstring key_word) {
	
	const char *fileName = env->GetStringUTFChars(file_name, NULL);
	const char *filePath = env->GetStringUTFChars(file_path, NULL);
	const char *end = env->GetStringUTFChars(endd, NULL);
	const char *keyword = env->GetStringUTFChars(key_word, NULL);
	
	cout<<fileName<<" "<<filePath<<" "<<end<<" "<<keyword<<endl;
	
	fstream (myfile);
	string filecontent;
	string s =end;
	int newline = 0;
	if(s == "\n")
		newline++;
	if(end == "\\n")
		cout<<"###############"<<endl;
	
	myfile.open(filePath);
	int start = 0, found =1, itr = 0,check = 0;
	if (myfile.is_open() )	
	{
		string cpy="";
			
			while(getline( myfile, filecontent))
			{
				//cout<<"--------\n\n"<<endl;
				//cout<<"Content -- "<<filecontent<<endl;
				
				filecontent = cpy + filecontent;
				//cout<<"cpy string  :  "<< cpy <<endl;
				//cout<<"cpy + filecotent :  "<<filecontent<<endl;
				//cout<<"File content "<<filecontent<<endl;
				found = filecontent.find(end);
				if(newline > 0)
					found = strlen(filecontent.c_str());
				if(found >= 0)
				{
				
					//cout<<"Find : "<<found<<endl;
					string matched = filecontent.substr( start, found ); 
					//cout<<"matched Content :  "<< matched <<endl;
					int found_matched = matched.find(keyword);
					if(found_matched>=0)
					{
						//cout<<"Matched string  :  "<< matched <<endl;
						link_with_JavaCode(matched, env);
					}
					filecontent.erase(start, found+1);
					//cout<<"After arase : 1  "<< filecontent <<endl;
					if(filecontent.find(keyword) == -1)
						filecontent = "";
					cpy = filecontent;
					//cout<<"After arase : 2  "<< filecontent <<endl;
					//cpy= "";
				
				}
				else	
					cpy = filecontent;
				} 


			
			/*else{
				getline( myfile, cpy, '\n');
				filecontent += cpy;
			}*/
	}
	
	else{
		cout<<"File is not opened"<<endl;
	}
	if(filecontent != "")
	{
		cout<<"Else if ....."<<endl;
		while(filecontent != "")
				{
					found = filecontent.find(end);
					if(found >= 0)
					{
						string matched = filecontent.substr( start, found ); 
						cout<<"matched Content :  "<< matched <<endl;
						int found_matched = matched.find(keyword);
						if(found_matched>=0)
						{
							cout<<"Matched string  :  "<< matched <<endl;
							link_with_JavaCode(matched, env);
						}
						filecontent.erase(start, found+1);
						cout<<"After arase :  "<< filecontent <<endl;
						//cpy = filecontent;
						//cpy= "";
					
					}
					else	
						break;
		}
	}
				
	
	
	
}
	
//	thread 1(link_with_JavaCode, matched, env);
			//link_with_JavaCode(matched, env);				threads[++itr] = thread(link_with_JavaCode, matched, env);
			