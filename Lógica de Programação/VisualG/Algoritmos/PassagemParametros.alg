algoritmo "Passagem_de_Parametros"
// Função : Exemplificar como funciona a passagem de parametros na linguagem de programação.
// Autor : Davi Carneiro Menez
// Data : 19/05/2020
// Seção de Declarações 
var
r1, r2, x, y, a: real
Procedimento Somatorio1 (p1: real)
    inicio
    p1 <- 2*p1
    r1 <- p1 + 4

    ESCREVAL(a, x, y, r1, r2)
FimProcedimento

Procedimento Somatorio2 (var p2, p3: real)
    inicio
    p2 <- 3*p2
    p3 <- 2*p3
    r2 <- p2 + p3
FimProcedimento

inicio
// Seção de Comandos
    x <- 1
    y <- 3
    a <- 5
    Somatorio1 (a)
    Somatorio2 (y, x)
    ESCREVAL(a, x, y, r1, r2)
fimalgoritmo