import javax.swing.*;
import java.awt.*;

public class OperationFrame extends JFrame {

    public OperationFrame(){
        setTitle("四则运算");
        JTextField jTextRange = new JTextField();
        JTextField jTextNumber= new JTextField();

        JTextField jTextField1=new JTextField();
        JTextField jTextField2=new JTextField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(4,3));
        northPanel.add(new JLabel("数值范围", SwingConstants.LEFT));
        northPanel.add(jTextRange);
        northPanel.add(new JLabel("生成题目数量", SwingConstants.LEFT));
        northPanel.add(jTextNumber);

        northPanel.add(new JLabel("校验文本1", SwingConstants.LEFT));
        northPanel.add(jTextField1);
        northPanel.add(new JLabel("校验文本2", SwingConstants.LEFT));
        northPanel.add(jTextField2);

        add(northPanel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(8, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane,BorderLayout.CENTER);

        JPanel southPanel=new JPanel();


        Button ComputeButton = new Button("Compute");
        Button CompareButton = new Button("Compare");


        southPanel.add(ComputeButton);
        southPanel.add(CompareButton);

        ComputeButton.addActionListener(event->{
           Main.range=Integer.parseInt(jTextRange.getText().trim());
           Main.OperatorNumber=Integer.parseInt(jTextNumber.getText().trim());
           Expression.GetExpression();
           textArea.append("计算完成\n");
        });

        CompareButton.addActionListener(event->{
           String path1=jTextField1.getText().trim();
           String path2=jTextField2.getText().trim();
            Compare.CompareTxt(path1,path2);
            textArea.append("比较完成\n");
        });

        add(southPanel,BorderLayout.SOUTH);

//        更新视图
        pack();

    }
}
