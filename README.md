# Interpreter Assignment

* Author: Broden
* Class: CS354
* Semester: Fall 2026

## Overview

This program takes in a string representation of a program and tokenizes it based on type. Comments
are skipped by the tokenization process since they will end up having no meaning to the program later 
on.

## Reflection

### Part 1: Lexical Analysis

This part of the assignment was fairly simple. Creating hashsets for each token type was easy as all I 
needed to do for that was look at an ascii table to see where and when I could use a range of values 
and when I would have to individually add values. The pre-stamped code was easy to understand and add
my work to and the given jUnit tests were great templates for working on making a black box test before 
actually starting work on the project (and adding to it during development as things changed). Overall
a very digestible project.
Issues did occur however when implementing the nextKw___() functions for example with the num tokenizer
my logic for checking to make sure two decimals did not occur in the same token would not execute. I had
to spend a bunch of time using the break point function in OSScode before realizing the issue was that my advance()
call at the beginning of the while loop was causing the check to happen after the token would've been peek()ed
adjusting the order of the advance() call to the end resolved the issue. I had a similar issue with commentParser()
where it would automatically skip and go out of the bounds of the string because of a issue with how the advance() call
was placed.

## Results

By the end of this part of the assignment all given tests have passed in jUnit and all of my tests have passed as well,
my tests focused on what I viewed to be edge cases or major changes such as newlines, mid token operators, Token
types being placed inside of comments, and Token types being placed right outside of comments. All of which resulted in
expected behavior.

### Part 1: Lexical Analysis

## Sources used

N/A 
