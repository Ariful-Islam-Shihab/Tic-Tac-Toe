import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TICTACTOEFRAME extends JFrame {
    static TICTACTOEFRAME TC=new TICTACTOEFRAME();
    static int turnChecker=1;
    static int[][] matrix=new int[3][3];
    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static int winCheck() {
        int flag = 0;

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2]) {
                flag = matrix[i][0];
                if (flag != 0) {
                    return flag;  // Return immediately if there's a winner
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i]) {
                flag = matrix[0][i];
                if (flag != 0) {
                    return flag;  // Return immediately if there's a winner
                }
            }
        }

        // Check diagonals
        if (matrix[0][0] == matrix[1][1] && matrix[1][1] == matrix[2][2]) {
            flag = matrix[0][0];
        } else if (matrix[0][2] == matrix[1][1] && matrix[1][1] == matrix[2][0]) {
            flag = matrix[0][2];
        }

        return flag;
    }

    public static void frameSetter(){
        TC.setVisible(true);
        TC.setDefaultCloseOperation(3);
        TC.setSize(400,400);
        TC.setLayout(null);
        JButton[][] buttons=new JButton[3][3];
        int width=50,height=40,x=0,y=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j]=new JButton();
                buttons[i][j].setBounds(x,y,width,height);
                int finalX = x;
                int finalY = y;
                int finalI = i;
                int finalJ = j;
                int finalWidth=width;
                int finalHeight=height;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TC.remove(buttons[finalI][finalJ]);
                        JTextField text=new JTextField();
                        text.setBounds(finalX, finalY,finalWidth,finalHeight);
                        if(turnChecker%2!=0){
                            text.setText("X");
                            matrix[finalI][finalJ]=1;
                        }else{
                            text.setText("O");
                            matrix[finalI][finalJ]=2;
                        }
                        System.out.println("Matrix after move:");
                        printMatrix(matrix);
//                        System.out.println(finalI+" "+finalJ);
                        int x=winCheck();
                        System.out.println("Winner check result: " + x); // Add this line for debugging
                        congratualtionWindow(x);
                        turnChecker++;
                        TC.add(text);
                    }
                });
                x=x+width+10;
                TC.add(buttons[i][j]);
            }
            y=y+height;
            x=0;
        }


    }
    static void congratualtionWindow(int x){
        if(x==1){
            TC.dispose();
            JOptionPane.showMessageDialog(null,"Player 1 won");
        }else if(x==2){
            TC.dispose();
            JOptionPane.showMessageDialog(null,"Player 2 won");
        }
        else
            return;
    }
}
