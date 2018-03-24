/*
 * Made in Turbo C... because that's what we're taught in Mumbai University.
 * Yeah fuck that. But we made it in 1 hr.
 */

#include <stdio.h>

#define PLAY_X 'X'
#define PLAY_O 'O'
#define BLANK  '-'
#define FILLED 'V'

// Grid shared with all functions.
static char grid [3][3];

// Struct to store values together.
struct pos {
	int x;
        int y;
};

// Clear the grid.
void empty () {
	int i, j;
        for (i = 0; i < 3; i++) {
        	for (j = 0; j < 3; j++) {
	        	grid [i][j] = BLANK;
                }
        }
}

// Display the grid.
void display () {
	int i, j;
        for (i = 0; i < 3; i++) {
        	for (j = 0; j < 3; j++)
              		printf ("%c ", grid[i][j]);
                printf ("\n");
        }
}

// Start the game.
int start () {
        int d;
	printf ("\n\n\n***PRESS 1 TO START OR 0 TO EXIT***\n");
        scanf ("%d", &d);
        return d;
}

// Check end-game conditions.
int check () {
	int i,j, diaL = grid [0][0], diaR = grid [0][2];

        for (i = 0; i < 3; i++) {
            // Check rows.
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2])
                return grid[i][0];
            else
            // Check columns.
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i])
                return grid[0][i];
            // Check left-right diagonal.
            if (diaL != BLANK && diaL != grid [i][i])
                diaL = BLANK;
            // Check right-left diagonal,
            if (diaR != BLANK && diaR != grid [i][2 - i])
                diaR = BLANK;
        }
        if (diaL != BLANK) return diaL;
        if (diaR != BLANK) return diaR;

        for (i = 0; i < 3; i++)
        	for (j = 0; j < 3; j++)
                	if (grid[i][j] == BLANK)
                        	return BLANK;

        // If all conditions fail then draw.
        return FILLED;
}

// Check if the choords are valid.
int checkcoords (int x, int y) {
	if (x == -2 || y == -2)
        	return 0;
	else if (grid [x][y] != BLANK)
        	return 1;
        else
        	return 0;
}

struct pos input (int player) {
        int x, y;
        struct pos coords;
        do {
		printf ("PLAYER %c -> (x, y) = ", player);
        	scanf  ("%d %d", &x, &y);
        } while (checkcoords (x-1, y-1));
        coords.x = x-1;
        coords.y = y-1;
        return coords;
}

int main () {
	int alt, res;
        struct pos coords;

        while (start ()) {
        	// Clear grid.
                empty ();

                // Alternator.
                alt = PLAY_X;
		while (check () == BLANK) {
                        display ();
                        coords = input (alt);
                        if (coords.x == -1) return -1;
                        grid [coords.x][coords.y] = alt;
                        alt = alt == PLAY_X ? PLAY_O : PLAY_X;

                        res = check ();
                        printf ("%c\n", res);
                        if (res == FILLED)
                        	printf ("DRAW");
                        else if (res != BLANK) {
                        	display ();
                        	printf ("WINNER IS %c!", res);
                        }
                        else continue;
                }
        }

        return 0;
}
