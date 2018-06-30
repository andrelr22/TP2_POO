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

all: $(MAIN:.java=.class)

$(MAIN:.java=.class): $(OPERADORA:.java=.class)

clean:
	$(RM) ./operadora/*.class
	$(RM) ./*.class
