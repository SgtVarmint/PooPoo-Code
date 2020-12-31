#include <stdio.h>

int bits(unsigned char character);
unsigned char rotate(unsigned char letter, int count);
void encrypt(unsigned char plainText[2048], unsigned char key[2048], int length, unsigned char cipherText[2048]);

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
  
  /*  for(i=0;i<messageLength;i++)
    putchar(plainText[i]);
  putchar("\n");

  for(i=0;i<keyLength;i++)
    putchar(key[i]);
  */  
        int keySum = key[keyLength - 1] % keyLength;

	key[0] = rotate((key[0]^key[keySum]), bits(key[keyLength-1]));

	keySum = keySum + key[0];
	keySum = keySum % keyLength;

	for(i=1;i<keyLength;i++){
		key[i]=rotate((key[i]^key[keySum]), bits(key[i-1]));
		keySum = keySum + key[i];
		keySum = keySum % keyLength;
	}

  encrypt(plainText,key,messageLength,cipherText);

  for(i=0;i<messageLength;i++)
    putchar(cipherText[i]);
}

void encrypt(unsigned char plainText[2048], unsigned char key[2048], int length, unsigned char cipherText[2048]){
  unsigned char cipher;
  int i;

  for(i=0;i<length;i++)
    cipherText[i]= (plainText[i])^(key[i]);
}

unsigned char rotate(unsigned char letter, int count)
{
  int i = 0;
  for(i=0;i<count;i++){
    if((letter&1) != 1){
      letter = letter >> 1;
    }
    else{
      letter = letter >> 1;
      letter = letter^64;
    }
  }
  return letter;
}

int bits(unsigned char character)
{
  int bitNumber = 0;
  while(character != 0){
    if((character&1)==1)
      bitNumber++;
    character = character>>1;
  }
  return bitNumber;
}
