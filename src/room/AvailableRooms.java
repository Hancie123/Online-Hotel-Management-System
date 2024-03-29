package room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import libs.JDBCRoom;
import libs.Room;




public class AvailableRooms {
	
	JFrame frame;
	JLabel customer;
	 JTable table;
	 JButton btn;
	 JScrollPane scrollPane;
	 DefaultTableModel modeltable;
	public AvailableRooms() {
		frame=new JFrame("Available Rooms");
		frame.setSize(450,400);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hotel.png")));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		
		JPanel north=new JPanel();
		north.setBackground(new Color(0,0,0));
		north.setPreferredSize(new Dimension(100,80));
		north.setLayout(new FlowLayout());
		frame.getContentPane().add(north, BorderLayout.NORTH);
		
		customer=new JLabel("AVAILABLE ROOMS", SwingConstants.CENTER);
		customer.setPreferredSize(new Dimension(300,70));
		customer.setForeground(Color.white);
		customer.setFont(new Font("Verdana", Font.BOLD, 18));
		north.add(customer);
		
		
		modeltable = new DefaultTableModel();
		table = new JTable(modeltable);
		JTableHeader h1=table.getTableHeader();
		h1.setBackground(Color.black);
		h1.setForeground(Color.white);
		h1.setFont(new Font("Verdana", Font.BOLD,15));
		table.setFont(new Font("Verdana", Font.PLAIN,16));
		table.setRowHeight(20);
		table.setGridColor(Color.white);
		table.setSelectionBackground(Color.BLACK);
		table.setSelectionForeground(Color.white);
		
		modeltable.addColumn("Room-ID");
		modeltable.addColumn("Room Type");
		modeltable.addColumn("Room Rate");
		modeltable.addColumn("Status");
		
		
		Room room=new Room();
		
		JDBCRoom jdbc2 = new JDBCRoom();
		ArrayList rooms = jdbc2.getAvailableRooms();
		if(rooms.size()>0) {
			for(int i=0; i<rooms.size(); i++) {
				Room p = (Room) rooms.get(i);
				Object []tmp= {p.getId(),
						p.getType(),
						p.getRate(),
								p.getStatus(),
								};
				modeltable.addRow(tmp);
			}
		}
		scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new AvailableRooms();
		

	}
}
