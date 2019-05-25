.PHONY: build run checkdeps
##
##                             		/**
##                              ////////**              zimonbizkit's scala learning workspace
##                   *****//////////////**
##    ,*******************//////////////**              https://github.com/zimonbizkit/scala-workspace
## ***********************//////////////**
## ***********************//////////////**
## ***********************//////////////**              Possible options for make are:
## ***********************//////////////*               ------------------------------------------------
## ***********************//////////,.....
## ************************,..............              up: 	Starts the dockerized environment
## ***********,......................../**              down:   Shuts down the dockerized environment
## .............................*///////**              shell:  Starts a shell in the dockerized environemt
##             .....,*****//////////////**              sbt:    Also starts a sbt shell in the main container
##    ,*******************//////////////**              build:  [TBD] I still don't know how build works so
## ***********************//////////////**              test:   Execute the test suite
## ***********************//////////////**              help:	Prints this help file
## ***********************//////////////**              amm: 	Starts an Ammonite REPL shell
## ***********************//////////////*
## ***********************/////////*......
## ************************,..............
## ***********,.......................,/**
## ............................,*///////**
##              ...,******//////////////**
##   *********************//////////////**
## ***********************//////////////**
## ***********************//////////////**
## ***********************//////////////**
## ***********************//////////////*
## ***********************/////////*
## **********************/*
## ***********
##   **/
##

help: Makefile
	@sed -n 's/^##//p' $<

up:
	docker-compose up -d

down:
	docker-compose down

shell:
	docker-compose exec scalasbt bash

sbt:
	docker-compose exec scalasbt sbt

amm:
	docker-compose exec scalasbt amm
