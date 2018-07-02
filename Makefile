# Universidade Federal de Minas Gerais
# Programacao Orientada a Objetos
# 2018-1
#
# Trabalho Pratico 1
# Sistema de Operadora de Celular
#
# Andre Lage
# Augusto Mafra
#

COMPILE = javac
DEBUG = -g
DESTINY_DIR = .
BLOCK_WARNINGS = -Werror -Xlint # Treat any compilation warning as error

.SUFFIXES: .java .class

.java.class:
	$(COMPILE) $(DEBUG) -d $(DESTINY_DIR) $(BLOCK_WARNINGS) $*.java

MAIN = Console.java

OPERADORA = ./operadora/Operadora.java

OPERADORA_ITEMS = ./operadora/[!O]*.java # tudo dentro de ./operadora que nao deve ser 'Operadora.java'

EXCECOES = ./excecoes/*.java

all: $(MAIN:.java=.class)

$(MAIN:.java=.class): $(OPERADORA:.java=.class)

$(OPERADORA:.java=.class): $(OPERADORA_ITEMS:.java=.class)

$(OPERADORA_ITEMS:.java=.class): $(EXCECOES:.java=.class)

clean:
	$(RM) ./excecoes/*.class
	$(RM) ./operadora/*.class
	$(RM) ./*.class
