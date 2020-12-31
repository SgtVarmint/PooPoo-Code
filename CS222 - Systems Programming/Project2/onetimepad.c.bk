#include <stdio.h>

unsigned char encrypt(unsigned char plainText[2048], unsigned char key[2048], int length);

int main(){
  unsigned char plainText[2048];
  unsigned char key[2048];
  unsigned char cipherText[2048];
  int messageValue;
  int messageLength=0;
  int keyLength=0, initialKeyLength=0;
  int i=0,j=0;
  
  do{
      messageValue = getchar();
      if((messageValue>0 && messageValue < 128)){
        plainText[i] = messageValue;
        messageLength++;
	i++;
      }
  }while( (messageValue!=255)&&(i<2048) && (messageValue!=EOF));

  
  int keyValue;
  
  do{
      keyValue=getchar();
      if( (keyValue>0 && keyValue < 128) ){
        key[j] = keyValue;
        initialKeyLength++;
	j++;
      }
   }while( (keyValue!=EOF) && (j<2048) );

  for(i=initialKeyLength, keyLength=initialKeyLength;i<messageLength;i++){
    key[i]=key[i%initialKeyLength];
    keyLength++;
  }
  
  printf("Message: \n");
  for(i=0;i<messageLength;i++)
    putchar(plainText[i]);
  putchar('\n');
  putchar('\n');
  
  printf("Key:");
  for(j=0;j<keyLength;j++)
    putchar(key[j]);
  
  printf("\nMessage: %d\nKey: %d\n\n", messageLength,keyLength);

  for(i=0;i<messageLength;i++)
    cipherText[i]=encrypt(plainText,key,messageLength);
  for(i=0;i<messageLength;i++)
    putchar(cipherText[i]);
}

unsigned char encrypt(unsigned char plainText[2048], unsigned char key[2048], int length){
  unsigned char cipher;
  int i;
  
  for(i=0;i<length;i++)
    cipher = (plainText[i])^(key[i]);
}
