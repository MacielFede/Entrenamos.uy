package ui.panels;

import interfaces.ControllerFactory;
import interfaces.UserInterface;

import javax.swing.*;
import java.awt.*;

public class ModifyUserDataPanel extends JPanel {
    private final UserInterface uc = ControllerFactory.getInstance().getUserInterface();
    private final JLabel title = new JLabel("Cambiar información basica del usuario");
    private final JLabel selectUser = new JLabel("Seleccione el usuario >");
    private JComboBox<String> selectUserComboBox;
    private final JSeparator separator = new JSeparator();
    private final JButton saveButton = new JButton("Guardar");
    private final JButton cancelButton = new JButton("Cancelar");
    private JTextField nameTextField = new JTextField();
    private JTextField emailTextField = new JTextField();
    private JTextField lastnameTextField = new JTextField();
    private final JLabel nameLabel = new JLabel("Nombre");
    private final JLabel lastnameLabel = new JLabel("Apellido");
    private final JLabel emailLabel = new JLabel("Email");
    private JComboBox<Integer> dayComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
    private JComboBox<Integer> monthComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
    private JComboBox<Integer> yearComboBox;
    private final JLabel bornDateDetailLabel = new JLabel("dd / mm / aa");
    private final JLabel bornDateLabel = new JLabel("Fecha de nacimiento");
    public ModifyUserDataPanel(){
        fetchUserList();
        initialize();
        this.setVisible(true);
    }

    private void fetchUserList(){
        selectUserComboBox = new JComboBox<>(uc.listUsersByNickname());
    }

    private void fetchSelectedUserData(String nickname){
        // Completar los input fields con la info del usuario y habilitarlos
    }

    private void initialize(){
        setLayout(null);
        title.setBounds(50, 0, 358, 30);
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        selectUser.setBounds(20, 39, 171, 20);
        selectUser.setFont(new Font("Arial", Font.PLAIN, 14));
        selectUser.setLabelFor(selectUserComboBox);

        selectUserComboBox.setBounds(191, 40, 217, 21);
        selectUserComboBox.setToolTipText("Nicknames de los usuarios");
        selectUserComboBox.addItemListener(e -> {
            // TODO: Setearle la info a todos los inputs
        });

        separator.setBounds(20, 70, 391, 2);

        saveButton.setBounds(106, 269, 85, 21);
        saveButton.setEnabled(false);

        cancelButton.setBounds(246, 269, 85, 21);
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(e -> {
            // Ejecutar algo
        });

        nameTextField.setBounds(20, 116, 119, 19);
        nameTextField.setColumns(10);
        nameTextField.setEditable(false);

        emailTextField.setBounds(20, 187, 119, 19);
        emailTextField.setEditable(false);
        emailTextField.setColumns(10);

        lastnameTextField.setBounds(222, 116, 119, 19);
        lastnameTextField.setColumns(10);
        lastnameTextField.setEditable(false);

        nameLabel.setBounds(20, 93, 45, 13);
        nameLabel.setLabelFor(nameTextField);

        lastnameLabel.setBounds(222, 93, 94, 13);
        lastnameLabel.setLabelFor(lastnameTextField);

        emailLabel.setBounds(20, 164, 45, 13);
        emailLabel.setLabelFor(emailTextField);

        dayComboBox.setBounds(222, 210, 29, 21);
        dayComboBox.setEnabled(false);

        monthComboBox.setBounds(262, 210, 29, 21);
        monthComboBox.setEnabled(false);

        yearComboBox.setBounds(302, 210, 29, 21);
        yearComboBox.setEnabled(false);

        bornDateDetailLabel.setBounds(222, 183, 94, 13);
        bornDateDetailLabel.setLabelFor(dayComboBox);

        bornDateLabel.setBounds(222, 164, 94, 13);
        bornDateLabel.setLabelFor(bornDateDetailLabel);

        add(separator);
        add(saveButton);
        add(cancelButton);
        add(nameTextField);
        add(emailTextField);
        add(lastnameTextField);
        add(title);
        add(selectUser);
        add(selectUserComboBox);
        add(lastnameLabel);
        add(emailLabel);
        add(bornDateLabel);
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
        add(nameLabel);
        add(bornDateDetailLabel);
    }
}
