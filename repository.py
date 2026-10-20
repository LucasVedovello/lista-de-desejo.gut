# Repository: guarda os dados
nomes = []
precos = []
prioridades = []
comprados = []


def cadastrar(nome, preco, prioridade):
    nomes.append(nome)
    precos.append(preco)
    prioridades.append(prioridade)
    comprados.append("nao")


def listar():
    for i in range(len(nomes)):
        print(i + 1, "-", nomes[i], "| R$", precos[i],
              "|", prioridades[i], "| Comprado:", comprados[i])


def remover(i):
    nomes.pop(i)
    precos.pop(i)
    prioridades.pop(i)
    comprados.pop(i)


def atualizar(i, campo, valor):
    if campo == "1":
        nomes[i] = valor
    elif campo == "2":
        precos[i] = valor
    elif campo == "3":
        prioridades[i] = valor


# Controller: controla o menu
def controller():
    while True:
        print("\n1 Cadastrar")
        print("2 Listar")
        print("3 Marcar como comprado")
        print("4 Atualizar")
        print("5 Remover")
        print("6 Relatorio")
        print("7 Sair")

        op = input("Escolha: ")

        if op == "1":
            nome = input("Nome: ")

            if nome == "" or len(nome) > 150:
                print("Nome invalido!")
                continue

            try:
                preco = float(input("Preço: "))
                if preco < 0:
                    print("Preço nao pode ser negativo!")
                    continue
            except ValueError:
                print("Digite um preço valido!")
                continue

            prioridade = input("Prioridade: ")
            cadastrar(nome, preco, prioridade)
            print("Cadastrado!")

        elif op == "2":
            if len(nomes) == 0:
                print("Lista vazia!")
            else:
                listar()

        elif op == "3":
            if len(nomes) == 0:
                print("Lista vazia!")
                continue

            listar()
            try:
                i = int(input("Numero do item: ")) - 1
                if 0 <= i < len(nomes):
                    comprados[i] = "sim"
                    print("Marcado como comprado!")
                else:
                    print("Item invalido!")
            except ValueError:
                print("Digite um numero!")

        elif op == "4":
            if len(nomes) == 0:
                print("Lista vazia!")
                continue

            listar()
            try:
                i = int(input("Numero do item: ")) - 1

                if i < 0 or i >= len(nomes):
                    print("Item invalido!")
                    continue

                print("1 Nome  2 Preço  3 Prioridade")
                campo = input("Qual campo deseja mudar? ")

                if campo == "1":
                    valor = input("Novo nome: ")
                    if valor == "" or len(valor) > 150:
                        print("Nome invalido!")
                        continue

                elif campo == "2":
                    try:
                        valor = float(input("Novo preço: "))
                        if valor < 0:
                            print("Preço invalido!")
                            continue
                    except ValueError:
                        print("Digite um numero!")
                        continue

                elif campo == "3":
                    valor = input("Nova prioridade: ")

                else:
                    print("Opcao invalida!")
                    continue

                atualizar(i, campo, valor)
                print("Atualizado!")

            except ValueError:
                print("Digite um numero!")

        elif op == "5":
            if len(nomes) == 0:
                print("Lista vazia!")
                continue

            listar()
            try:
                i = int(input("Numero do item: ")) - 1

                if i < 0 or i >= len(nomes):
                    print("Item invalido!")
                    continue

                confirmar = input("Deseja remover? (s/n): ")

                if confirmar == "s":
                    remover(i)
                    print("Removido!")
            except ValueError:
                print("Digite um numero!")

        elif op == "6":
            print("Total:", len(nomes))
            print("Comprados:", comprados.count("sim"))
            print("Faltam:", comprados.count("nao"))

        elif op == "7":
            print("Saindo...")
            break

        else:
            print("Opcao invalida!")


controller()
