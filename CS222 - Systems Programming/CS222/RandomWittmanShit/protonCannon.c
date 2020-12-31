#include <stdio.h>
#include <math.h>

int main(){
  double x = 64.4, y = 72.3;

  double theta = atan2(y,x);
  printf("Shoot at angle %f radians\n", theta);
  
  return 0;
}
