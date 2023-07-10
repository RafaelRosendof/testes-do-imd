#include<stdio.h>
#include<stdlib.h>
#include<time.h>
//parte para as funções agora 

//função para a jogada aleatória do computador
void jogadaComp(char **tabu){
    srand(time(NULL));
    int posi;

    do{
        posi = rand() %9+1; //posição do tabuleiro
        int lin = (posi - 1) /3;
        int col = (posi -1) %3; //logica para o computador jogar, msm da função do jogador, é explicado lá em baixo 

        if(tabu[lin][col] != 'X' && tabu[lin][col] != 'O'){
            tabu[lin][col] = 'O';
            break;
        }
    }while(1); //enquanto for a vez do computador 
}


void jogada(char **tabu){ //função para ler a jogada do jogador
    int posi;
    printf("Digite o local que vai jogar do 1 ao 9: ");
    scanf("%d",&posi);

    int lin = (posi -1)/3;   // a matriz vai de 1 a 9 precisamos colocar primeiro de 0 a 8 dps para 0 1 ou 2 
    int col = (posi - 1) %3; //tratando os locais corretos para a matriz 

    //agora é o tratamento com if para saber se a jogada foi valida ou não 
    if(tabu[lin][col] == 'X' || tabu[lin][col] =='O'){
        printf("Jogada inválida\n");
        jogada(tabu);
    }
    else{
        tabu[lin][col] = 'X';
    }

}

//função para liberar a matriz alocada dinamicamente, a cada linha e coluna 
void freeTabu(char **tabu){
    for(int i=0;i<3;i++){
        free(tabu[i]);
    }
    free(tabu);
}


void TabImag(char** tabu) { //função para exibir o tabuleiro
    printf("\n");
    printf(" %c | %c | %c \n", tabu[0][0], tabu[0][1], tabu[0][2]);
    printf("---+---+---\n");
    printf(" %c | %c | %c \n", tabu[1][0], tabu[1][1], tabu[1][2]);
    printf("---+---+---\n");
    printf(" %c | %c | %c \n", tabu[2][0], tabu[2][1], tabu[2][2]);
    printf("\n");
}

int verificador(char **tabu){
    //verificar linhas, colunas, diagonais e o empate 



        //diagonais 
    if((tabu[0][0] == tabu[1][1] && tabu[1][1] == tabu[2][2]) || (tabu[0][2] == tabu[1][1] && tabu[1][1] == tabu[2][0])){
        if(tabu[1][1] == 'X')
            return 1;
        else if(tabu[1][1]=='O')     //1 é o jogador o 2 é o pc 
            return 2;
    }  

    //agora linhas...
    for(int i=0;i<3;i++){
        if(tabu[i][0] == tabu[i][1] && tabu[i][1] == tabu[i][2]){
            if(tabu[i][0] == 'X')  //indice i e 0 pois é comum aos dois 
                return 1;
            else if(tabu[i][0] == 'O')
            return 2; 
        }
    }

    for(int i=0;i<3;i++){
        if(tabu[0][i] == tabu[1][i] && tabu[1][i] == tabu[2][i]){
            if(tabu[0][i] == 'X')
                return 1;
            else if(tabu[0][i] == 'O')
                return 2;   //colunas verificadas 
        }
    }

    //empate
    int empate =1;
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            if(tabu[i][j] != 'X' &&  tabu[i][j] != 'O'){
                empate=0;
                break;
            }
        }

        if(!empate)
            break;
    }
    if(empate)
        return 3; //deu empate

    return 0; //continua jogando...
}

//parte do main
int main(){
    char** tabu = (char **)malloc(3* sizeof(char*));
    for(int i=0;i<3;i++){        
        tabu[i] = (char*)malloc(3 * sizeof(char));
    }

    int valor =1;
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            tabu[i][j] = valor + '0';
            valor ++;
            //colocando os valores no tabuleiro 3x3 de 1 até 9
        }
    }

    int vezJog = 1; //vezJog = 1 sou eu vezjog = 2 é o pc 
    int venceu =0; //venceu =1 eu ganho venceu =2 pc ganha venceu =3 da empate

    while(venceu ==0){
        TabImag(tabu);

        if(vezJog ==1){
            jogada(tabu);
            vezJog = 2; //fazendo a logica do jogo, jogador joga próxima é do pc e vice versa
        }
        else{
            jogadaComp(tabu);
            vezJog=1;
        }
        venceu = verificador(tabu);
    } 

    TabImag(tabu);

    if(venceu==1)
        printf("Parabens, voce venceu\n\n");
    else if(venceu ==2 )
        printf("Computador venceu\n\n");//printando o resultado
    else
        printf("Deu empate\n\n");

    freeTabu(tabu);

    return 0;
}