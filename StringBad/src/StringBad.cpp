/*
 * strngbad.cpp
 *
 *  Created on: 2013-7-23
 *      Author: Reid
 */

#include <cstring>
#include <iostream>
#include <new>
#include <string>

#include "strngBad.h"

using std::cout;

int StringBad::num_strings = 0;

StringBad::StringBad(const char *s){
	int len = std::strlen(s);
	str = new char[len + 1];
	std::strcpy(str, s);
	num_strings++;
	cout << num_strings << ": \"" << str << "\" object created\n";
}

StringBad::StringBad(){
	int len = 4;
	str = new char[len + 1];
	std::strcpy(str, "C++");
	num_strings++;
	cout << num_strings << ": \"" << str << "\" default object created\n";
}

StringBad::StringBad(const StringBad &st){
	num_strings++;
	str = new char[(std::strlen(st.str)) + 1];
	std::strcpy(str, st.str);
	cout << num_strings << ": \"" << str << "\" object created\n";
}

StringBad & StringBad:: operator=(const StringBad &st){
	if(this->str == st.str){
		return *this;
	}
	delete [] str;
	str = new char[std::strlen(st.str) + 1];
	std::strcpy(str, st.str);
	cout << num_strings << ": \"" << str << "\" object created\n";
	return *this;
}

StringBad::~StringBad(){
	cout << "\"" << str << "\" object deleted, ";
	--num_strings;
	cout << num_strings << " left \n";
	delete [] str;
}

std::ostream & operator<<(std::ostream &os, const StringBad &st){
	os << st.str;
	return os;
}
