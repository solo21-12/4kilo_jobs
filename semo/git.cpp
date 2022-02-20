#include <iostream>
using namespace std;
bool even();
int num;
int main(){
    cout<<"enter a number ";cin>>num;
    cout<<even();
}
bool even(){
    if(num%2==0){
        cout<<"true";
    }
    else{
        cout<<"false";
    }
    return 0;
}