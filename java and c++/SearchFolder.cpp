#include <jni.h>      
#include <iostream>    
#include <dirent.h>
#include <string> 
#include <fstream>
#include <Windows.h> 
#include<sstream>
#include "main.h"  
#include <direct.h>
using namespace std;

void link_with_JavaCode(string line, JNIEnv *env)
{
	
	jclass output_class = env->FindClass("Main");
	
	try
	{
		jmethodID id = env->GetMethodID(output_class,"<init>", "()V");

		jobject myobj = env->NewObject(output_class, id);
		jmethodID show = env->GetMethodID(output_class, "sendOutput", "(Ljava/lang/String;)V");
		jstring jStringParam = env->NewStringUTF(line.c_str());
		

		env->CallVoidMethod(myobj, show, jStringParam);
		
	}
	catch(...)
	{
		cout<<"Error ..."<<endl;
	}
}

void generatingOutput(JNIEnv *env, string path)
{
	string finalFolder = "Metadata_Folder";
   
	string finalPath = path + finalFolder;
	
	struct dirent *entry;
	DIR *dir = opendir(finalPath.c_str());
	
	cout<<endl<<"Final path .. "<< finalPath<<endl<<endl;
	
	if (dir == NULL) {
		cout<<"Invalid path"<<endl;
		return;
	}
	string output="";
	
	while ((entry = readdir(dir)) != NULL) {
		
		string name = entry->d_name;
		ostringstream ss;
		
		ifstream f(finalPath+"\\"+name);
		if(f)
		{
			ss << f.rdbuf();
			output += ss.str();
			output += "----------------------\n";
		}
	   
	}
   
	//cout<<endl<<"Output!!!!!!!!!!!!&&&&&&&&&"<<endl <<endl<<output <<endl;
	link_with_JavaCode(output, env);
}

void files(string path,string name)
{
    WIN32_FILE_ATTRIBUTE_DATA fileInfo;
	FILETIME ft;
	
	mkdir((path + "Metadata_Folder").c_str());  //creates new folder
	
	cout<<endl<<endl<<"Directory  "<< path + "Metadata_Folder"<<endl << name<<endl;
	
    if(GetFileAttributesEx((path+name).c_str() , GetFileExInfoStandard, &fileInfo))
    {
		
        fstream file;
        file.open( path+"Metadata_Folder\\" + name + "_meta.txt", ios::trunc | ios::out );
        SYSTEMTIME time;
        int  fileSize = fileInfo.nFileSizeLow;
        file<<"File name: \n"<<name<<endl;
        file<<"File path: \n"<<path<<endl;
        file<<"File Size: \n"<<fileSize<<" bytes"<<endl;
		

        if(FileTimeToSystemTime(&fileInfo.ftCreationTime, &time) )
            file<<"Date created: "<<"\n"<<time.wDay<<"/"<<time.wMonth<<"/"<<time.wYear<<" , "<<time.wHour<<":"<<time.wMinute<< endl;
		
        if( FileTimeToSystemTime(&fileInfo.ftLastAccessTime, &time) )
            file<<"Date last Accessed:"<<"\n"<<time.wDay<<"/"<<time.wMonth<<"/"<<time.wYear<<" "<<time.wHour<<":"<<time.wMinute<< endl;
		
        if(FileTimeToSystemTime(&fileInfo.ftLastWriteTime, &time) )
            file<<"Date last Modified:"<<"\n"<<time.wDay<<"/"<<time.wMonth<<"/"<<time.wYear<<" , "<<time.wHour<<":"<<time.wMinute<< endl;
		
	}
    else
        cout<<"file not found"<<endl;
}

void list_dir(const char *path, const char *pattern, JNIEnv *env) {
   struct dirent *entry;
   DIR *dir = opendir(path);
   int flag = 0;
   if (dir == NULL) {
	   cout<<"Invalid path"<<endl;
      return;
   }
   while ((entry = readdir(dir)) != NULL) {
	   
	   string name = entry->d_name;
	   int found = name.find(pattern);
	   DIR *dir2 = opendir((path+name).c_str());
	   if( dir2 == NULL && found>=0)
	   {
		   flag = 1;
		   files(path,name);
	   }
   }
   
   if(flag > 0)
	   generatingOutput(env, path);
   
   closedir(dir);
}


JNIEXPORT void JNICALL Java_Main_folderSearch(JNIEnv *env, jobject thisObj,jstring folder_path, jstring pattern) {
	
	char* cname;
	const char *folderPath = env->GetStringUTFChars(folder_path, NULL);
	const char *pattern_match = env->GetStringUTFChars(pattern, NULL);
	cout<<"Folder Path "<<folderPath<<" Pattern "<<pattern_match<<endl;
	
	list_dir(folderPath,pattern_match, env);
   return;
}

/*
char tofind = '.';
	int last_occ = name.find_last_of(tofind);
	//int found = name.find(tofind); 
  	name.erase(last_occ);
	name += "_metadataFile.txt";
  	cout<<"Output File name : "<<name<<"..."<<nameCopy<<endl;
	cout<<"File ppath "<< path+name<<endl;
	
	   else if(dir2 !=NULL && found>=0){
	   cout<<"Path + name  !!!!! "<<path+name<<endl;
	   }
*/
