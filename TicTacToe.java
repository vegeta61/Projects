import java.util.Scanner;
class TicTacToe {
	public static boolean isBoardFull(char[] moves) {
		for(int i = 0; i < moves.length; i++) {
			if(moves[i] == 0)
				return(false);
		}

		return(true);
	}
	public static int wins(char moves[], char ch, char ch1) {
		int arr[][] = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
		int brr[] = new int[5];
		for(int i = 0; i < brr.length; i++) {
			brr[i] = -1;
		}
		int pa = 0;
		for(int i = 0; i < moves.length; i++) {
			if(moves[i] == ch)
				brr[pa++] = i;
		}
		int r = 0, p = 0, num = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < pa; j++) {
				for(int k = 0; k < 3; k++) {
					if(arr[i][k] == brr[j]) {
						num++;
						if(num == 1)
							r = k;
						if(num == 2)
							p = k;
						break;
					}
				}
				if(num == 2) {
					int q = 0;
					if(r == 0 && p == 1 || r == 1 && p == 0)
						q = 2;
					else if(r == 2 && p == 0 || p == 2 && r == 0)
						q = 1;
					if(moves[arr[i][q]] == 0)
						return(arr[i][q]);
				}	
			}
			num = 0;
		}
		return(-1);
	}
	public static boolean isWinner(char moves[], char ch) {
		int arr[][] = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
		int brr[] = new int[5];
		for(int i = 0; i < brr.length; i++) 
			brr[i] = -1;
		int pa = 0;
		for(int i = 0; i < moves.length; i++) {
			if(moves[i] == ch)
				brr[pa++] = i;
		}
		int num = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < pa; j++) {
				for(int k = 0; k < 3; k++) {
					if(arr[i][k] == brr[j]) {
						num++;
						break;
					}
				}
				if(num == 3) {
					return(true);
				}	
			}
			num = 0;
		}
		return(false);
	}
	
	public static int move(char moves[], int num) {
		if(num == 5) {
			if(moves[4] == 'x')
				return(0);
			else
				return(4);
		}
		else {
			if(wins(moves, 'o', 'x') != -1) {
				return(wins(moves, 'o', 'x'));
			}
			else if(wins(moves, 'x', 'o') != -1)
				return(wins(moves, 'x', 'o'));
			else {
				if(moves[4] == 'o') {
					if(moves[7] == 0)
						return(7);
					if(moves[5] == 0)
						return(5);
				}
				int r = (int)Math.random() * 100 % 9;
				if(moves[r] != 0)
					r = (int)Math.random() * 100 % 9;

				return(r);
			}
		}
	}
	public static void draw(char moves[]) {
		int r = 0;
		for(int k = 1; k < 12; k++) {
			if(k % 4 != 0)
			for(int i = 1; i < 12; i++) {
				if((k == 2 || k == 6 || k == 10) && (i == 2 || i == 6 || i == 10)) {
					System.out.print(" " + moves[r++]);
				}
				else if(i % 4 != 0)
					System.out.print("  ");
				else 
					System.out.print("  |  ");
			}
			else {
				for(int i = 0; i < 28; i++) {
					System.out.print("-");
				}	
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char moves[] = new char[9];
		for(int i = 0; i < moves.length; i++) {
			moves[i] = 0;
		}
		System.out.println("Welcome To\nUnbeatable Tic Tac Toe...");
		System.out.println("For Convience...\nPlayer is X\nComputer is O");
		System.out.println("Let's Start The Game\n\n");
		draw(moves);
		int num = 5;
		abc : while(num > 0) {
			
			System.out.print("\nEnter Position To Fill : ");
			int pos = sc.nextInt();
			pos--;
			if(pos < 0 || pos > 12) {
				System.out.println("Enter Valid Position");
				continue;
			}
			if(moves[pos] != 0) {
				System.out.println("Position Already Occupied");
				continue;
			}
			moves[pos] = 'x';
			if(isWinner(moves, 'x')) {
				draw(moves);
				System.out.println("Player Wins....");
				num = 7;
				break;
			}
			if(isBoardFull(moves))
				break;
			pos = move(moves, num);
			while(moves[pos] != 0) {
				pos = move(moves, num);
			}
			moves[pos] = 'o';
			if(isWinner(moves,'o')) {
				draw(moves);
				System.out.println("Computer Wins....");
				num = 7;
				break;
			}
			draw(moves);
			num--;
		}
		if(num != 7){
			draw(moves);
			System.out.println("Game Draw");
		}
		System.out.println("Game End ...");
	}
}