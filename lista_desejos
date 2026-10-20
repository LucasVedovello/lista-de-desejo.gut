nomes = []
precos = []
prioridades = []
comprados = []

def cadastrar():
    nome = input("Nome do item: ")

    if nome == "" or len(nome) > 150:
        print("Nome invalido!")
        return

    try:
        preco = float(input("Preço: "))
        if preco < 0:
            print("Preço nao pode ser negativo!")
            return
    except:
        print("Digite um preço valido!")
        return

    prioridade = input("Prioridade: ")

    nomes.append(nome)
    precos.append(preco)
    prioridades.append(prioridade)
    comprados.append("nao")

    print("Item cadastrado!")


def listar():
    if len(nomes) == 0:
        print("Lista vazia!")
    else:
        for i in range(len(nomes)):
            print(i + 1, nomes[i], precos[i],
                  prioridades[i], comprados[i])


def comprar():
    listar()

    if len(nomes) > 0:
        try:
            i = int(input("Numero do item: ")) - 1

            if i >= 0 and i < len(nomes):
                comprados[i] = "sim"
                print("Item comprado!")
            else:
                print("Item invalido!")
        except:
            print("Digite um numero!")


def remover():
    listar()

    if len(nomes) > 0:
        try:
            i = int(input("Numero do item: ")) - 1

            if i >= 0 and i < len(nomes):
                confirmar = input("Remover item? (s/n): ")

                if confirmar == "s":
                    nomes.pop(i)
                    precos.pop(i)
                    prioridades.pop(i)
                    comprados.pop(i)
                    print("Item removido!")
        except:
            print("Numero invalido!")


def atualizar():
    listar()

    if len(nomes) > 0:
        try:
            i = int(input("Numero do item: ")) - 1

            if i >= 0 and i < len(nomes):
                nomes[i] = input("Novo nome: ")
                print("Nome atualizado!")
        except:
            print("Numero invalido!")


def controller():
    while True:
        print("\n1 Cadastrar")
        print("2 Listar")
        print("3 Marcar como comprado")
        print("4 Atualizar")
        print("5 Remover")
        print("6 Relatorio")
        print("7 Sair")

        opcao = input("Escolha: ")

        if opcao == "1":
            cadastrar()
        elif opcao == "2":
            listar()
        elif opcao == "3":
            comprar()
        elif opcao == "4":
            atualizar()
        elif opcao == "5":
            remover()
        elif opcao == "6":
            print("Total:", len(nomes))
            print("Comprados:", comprados.count("sim"))
            print("Faltam:", comprados.count("nao"))
        elif opcao == "7":
            print("Saindo...")
            break
        else:
            print("Opcao invalida!")


controller()
