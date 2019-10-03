.PHONY: build run test consola jar clean all

FILES=$(find ./src/main/java/* | grep -e "\.java")
TEST_FILES := $(shell find tests -type f -regex ".*\.xml" | sort)
TEST_FILES_PARAMETERS := $(addprefix ../../../../, $(TEST_FILES))
BUILD_DIR=build
GRADLE_BIN=./gradlew
CLASSES_DIR=$(BUILD_DIR)/classes/java/main

all: clean jar

jar:
	@$(GRADLE_BIN) distZip

run:
	@$(GRADLE_BIN) run

consola: build
	@cd $(CLASSES_DIR) && java habitaciones.dominio.controladores.CtrlPrincipal

test:
	@cd $(CLASSES_DIR) && java habitaciones.dominio.controladores.test.Tester $(TEST_FILES_PARAMETERS)

build:
	@$(GRADLE_BIN) build

clean:
	@$(GRADLE_BIN) clean
