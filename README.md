# Automaton Simulator

A desktop tool for building and testing **deterministic pushdown automata**, with a timed game for
practising reading them. Written in Java (Swing) for my university dissertation in 2021.

<!-- TODO: a screenshot of the Simulator tab with the example automaton below laid out, e.g.
![The simulator with a two-state automaton accepting equal numbers of 0s and 1s](docs/simulator.png) -->

## Why

Automata theory is usually taught with lectures, textbooks and pen and paper, and pushdown
automata are a topic many students find hard to picture. The dissertation asked whether software,
and **gamification** in particular, could help: a tool to use alongside lectures, where students
build automata, watch inputs run through them, and test their understanding in games.

It started with a review of existing simulators (JFLAP, an online automaton simulator, a mobile
app and jFAST) and of interface design for learning tools: drawing automata in the standard
textbook notation, and keeping the interface simple so it doesn't add to the learner's load. That
gave the requirements: building any pushdown automaton by clicking and dragging, testing and
stepping through inputs, and three games, each harder than the last.

## What it does

**Simulator:** build your own automaton and run inputs through it.

- Add and delete states, choose the start state, and right-click a state to make it accepting.
- Set the input and stack alphabets and the stack's start symbol.
- Add a transition by clicking its start and end states, then filling in the input symbol, the
  symbol popped and what's pushed (`e` pushes nothing).
- Drag states around; transitions are drawn as labelled arrows between them.
- **Test** an input to see whether it's accepted, or test many at once with the bulk input box.
- **Step** through an input one transition at a time, showing the transition taken and the stack
  after each one.

**Game 1:** pick one of three levels and an automaton appears; you have a time limit to find as
many accepted inputs as you can. Correct answers are listed so they can't be reused, and you get a
score at the end.

**Instructions:** a tab explaining all of the above.

Two more games were designed but not built in the time: tracing an input by clicking the states it
passes through, and building an automaton for a described language (such as 0ⁿ1ⁿ). Saving and
loading automata, and non-deterministic automata, were also left for later.

## Running it

Needs a JDK (11 or later).

```sh
javac -d out src/*.java
java -cp out GUI
```

### An automaton to try

The dissertation's first test case, a classic from Hopcroft and Ullman, accepts strings with the
same number of 0s and 1s:

- States `s1` (start, accepting) and `s2`; input alphabet `01`; stack alphabet `ZAB`; stack
  start symbol `Z`
- Transitions, written (state, input, top of stack) → (new state, pushed):
  - (s1, 0, Z) → (s2, ZA) and (s1, 1, Z) → (s2, ZB)
  - (s2, 0, A) → (s2, AA) and (s2, 1, B) → (s2, BB)
  - (s2, 0, B) → (s2, e) and (s2, 1, A) → (s2, e)
  - (s2, e, Z) → (s1, Z)

`000111`, `10011010` and the empty string are accepted; `011011` and `000` are rejected.

## Code

| File                                                | What it is                                                          |
| --------------------------------------------------- | ------------------------------------------------------------------- |
| `pushdown.java`                                     | The automaton: states, alphabets, transitions, running and stepping |
| `state`, `symbol`, `alphabet`, `transition`, `tape` | Its parts                                                           |
| `GUI.java`                                          | The window, its tabs and controls, and the game's timer and levels  |
| `automatonPanel.java`                               | Drawing the automaton and dragging its states                       |
| `stateUI.java`                                      | A state on screen                                                   |
| `textPanel.java`                                    | The Instructions tab                                                |
| `consoleIO.java`                                    | An earlier text-only interface                                      |

This is the code as submitted in 2021, kept as it was.
