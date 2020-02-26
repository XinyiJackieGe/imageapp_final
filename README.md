# imageapp_neu
> This app provides several image scripting operations for user to 
apply on their images. It also generates images with interesting 
patterns as user required. Please make fun through our graphical user interface!

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)

## General info
This app is an exercise of MVC architecture pattern. It provides operations such as 
blurring, sharpening, setting Sepia, grey scale and dither tones and mosaic effect on images. 
So that user can play with images through the app GUI as they like. It also 
generates images with checkboard, and rainbow patterns with user input settings. 

## Technologies
* Java - version 8

## Setup
* Open terminal and set ditrctory to image_app/res.
* Run java -jar image_app.jar input.txt.
* Run java -jar image_app_final.jar.

## Input Examples
Show examples of input command lines:<br />
 load vaultboy.jpg <br />
 blur <br />
 save vaultboy_blur.jpg <br />
 load vaultboy.jpg <br />
 sharpen <br />
 save vaultboy_sharpen.jpg <br />
 load vaultboy.jpg <br />
 greyscale <br />
 save vaultboy_grey.jpg <br />
 load vaultboy.jpg <br />
 sepia <br />
 save vaultboy_sepia.jpg <br />
 rainbow 10 H 250 250 <br />
 save rainbow.jpg <br />
 checkboard 25 500 500 <br />
 save checkboard.jpg <br />
 load vaultboy.jpg <br /> 
 dithering <br />
 save vaultboy_dither.jpg <br />
 

## Features
List of features ready and TODOs for future development
* Accept various types of pictures.
* Various filtering operations provided.
* Generate interesting patterns with different options.
* Execute commands together given a command lines text file.


## Status
Project is: _finished_

* Updated dither and mosaic operations. 
* Create Controller in AppRunner to execute command lines given input.txt. 
* Graphical interactive interface.

## Inspiration
Gamingheads website has the copyright of "Vault Boy" picture 
(https://www.gamingheads.com).
User can only play with the images and save them locally. 
Cannot be used for commercial purposes.
