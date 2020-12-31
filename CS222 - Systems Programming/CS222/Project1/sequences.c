#include <stdio.h>
#include <math.h>

void lazyCaterer(int startNum, int limit);
void primeNumbers(int startNum, int limit);
long long fibonacci(long long startNum, long long b, int limit);
void collatz(int startNum, int limit);/*startNum must be > 1, limit is the value at which the loops stops running*/
void happyNumbers(int startNum, int limit);

int main(){
  printf("\nLazy Caterer's Sequence:\n");
  lazyCaterer(0,50);
  printf("\n\nPrime Number:\n");
  primeNumbers(2,50);
  printf("\n\nFibonacci Sequence:\n%d ",1);
  fibonacci(1,2,50);
  printf("\n\nCollatz Stopping Times:\n");
  collatz(1,50);
  printf("\n\nHappy Numbers:\n");
  happyNumbers(1,50);
  return 0;
}

void lazyCaterer(int startNum, int limit){

  printf("\n");
}

void primeNumbers(int startNum, int limit){
  int numCount=0;
  
  printf("\n");
}

/*
  Recursive FIbonacci Function
  startNum: number at which the function begins
  b: used to increment the value to find the next val in the Fibo sequence
  limit: the val at which the function stops running
*/
long long fibonacci(long long startNum, long long b, int limit){
  if(limit == 1)
    return 1;
  else{
    printf("%lld ",startNum);
    return fibonacci(b, startNum+b, limit-1);
  }
}

/*
  Used to find the Collatz Stopping Times up to 50
  startNum: number from which the function runs
  limit: number at which the fcuntion terminates
 */
void collatz(int startNum, int limit){
  int numCount=0;/*used to run up until the upper limit*/
  while(numCount<limit){
    int num = startNum;/* sets num to startNum; w/o this, startNum would always resolve back to 1 until the loop ends and never increment beyond*/
    int steps=0;/* keeps track of steps necessary to reach 1*/
    while(num!=1){
      if(num%2==0)/*is num is even*/
	num/=2;
      else if(num%2!=0)/*if num is odd*/
	num = (num*3)+1;
      steps++;
    }
    printf("%d ", steps);/*prints steps*/
    /* increment operation */
    numCount++;
    startNum++;
  }
  printf("\n");
}

void happyNumbers(int startNum, int limit){
  int numCount=0;
  while(numCount < limit){
    int num = startNum;
    int power = 10;/* used to mod to get individual digits*/
    int digitSquare=0;
    while(num%power>0){
      digitSquare+= num%power;
      power*=10;
    }

    
    
    numCount++;
  }
  printf("\n");
}
