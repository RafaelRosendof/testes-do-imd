#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

//função para printar
//função para resolver o jogo 
//função para verificar se tem rainhas conflitantes 

void printftabu(int **front, int tam) {
    for (int i = 0; i < tam; i++) {
        for (int j = 0; j < tam; j++) {
            if (front[i][j] == 1) {
                printf("Q ");
            } else {
                printf(". ");    //função para printar o tabuleiro NxN
            }
        }
        printf("\n");
    }
}

bool verifica(int **front, int rainha, int col, int tam){
    //verificar se ha rainhas na mesma linha , coluna  e diagonais 
    int i,j;

    for(i=rainha,j=col;j>=0 && i<tam;i++,j--){  //verificando a diagonal inferior 
        if(front[i][j]==1)
            return false;
    }

    for(i = rainha,j= col; i>=0 && j>=0;i--,j--){  //verificando a diagonal superior, começa lá de baixo e vai "subindo" até chegar nos menores indices 
        if(front[i][j]==1)
            return false;
    }

    //agora para as linhas e colunas, varrendo a matriz linha por linha 
    for(i=0;i<col;i++){
        if(front[rainha][i]==1)
            return false;
    }

    return true; //caso os testes estejam validos da para colocar a rainha no local
}

bool Resolve(int **front,int col,int tam){
    if(col >=tam)
        return true;  //verifica se ainda há espaços suficientes de rainhas com relação as lihas, pois só pode ter uma rainha por linha e coluna 

    for(int i=0;i<tam;i++){
        if(verifica(front,i,col,tam)){
            front[i][col] = 1;  //colocando a rainha na posição (i,col)

            if(Resolve(front,col+1,tam)){
                return true; //chamando a função dentro da propria função para verificar novamente, fznd uma recursao
            }

        front[i][col] = 0; //remove a rainha da posição (i,col)
        }
    }

    return false;  //caso seja falso não é possivel de resolver para valores menores que 8 
}


int main(){
    int tam;
    printf("Qual o tamanho do tabuleiro?: ");
    scanf("%d",&tam);

    int **front = (int **)malloc(tam * sizeof(int*)); //alocando a memoria da matriz do tabuleiro de xadrez
    for(int i=0;i<tam;i++){
        front[i] = (int*)malloc(tam * sizeof(int));
    }

    for(int i=0;i<tam;i++){
        for(int j=0;j<tam;j++){  //cobrindo o tabuleiro de 0's e dps vou preencher com pontos e Q's 
            front[i][j] = 0;
        }
    }

    if(Resolve(front,0,tam)){
        printf("\n");
        printftabu(front,tam);   //logica do jogo
    }
    else
        printf("Impossivel resolver esse problema\n ");

    for(int i=0;i<tam;i++){
        free(front[i]);   //liberando a memoria do tabuleiro 
    }
    free(front);

    return 0; 
}