package ui.Panels;

import dataTypes.DtActivity;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ModifyActivityPanel extends JPanel {
        private final InstituteInterface ic = ControllerFactory.getInstance().getInstituteInterface();
        private JComboBox<String> namesComboBox;
        private JTextArea descriptionTextArea = new JTextArea();
        private JFormattedTextField priceTextField = new JFormattedTextField();
        private JFormattedTextField durationTextField = new JFormattedTextField();
        private JTextField dateTextField = new JTextField();
        JButton cancelButton = new JButton("Cancelar");
        JButton saveButton = new JButton("Guardar");

        public ModifyActivityPanel(){
            this.setLayout(null);
            fetchActivitylist();
            initialize();
        }

        private void fetchActivitylist(){ namesComboBox = new JComboBox<>(ic.listSportActivitiesByName()); }
        private void fetchSelectedActivityData(String name){
            DtActivity activityChosen = ic.getActivity(name);
            if(Objects.isNull(activityChosen)){
                return;
            }
            // Sets the inputs with the activity information
            descriptionTextArea.setText(activityChosen.getDescription());
            descriptionTextArea.setEnabled(true);
            priceTextField.setValue(activityChosen.getPrice());
            priceTextField.setEnabled(true);
            durationTextField.setValue(activityChosen.getDuration());
            durationTextField.setEnabled(true);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = dateFormat.format(activityChosen.getRegistryDate());
            dateTextField.setText(formattedDate);
            saveButton.setEnabled(true);
            cancelButton.setEnabled(true);
        }
        private void updateActivityInfo(){
            String name = (String) namesComboBox.getSelectedItem();
            String description = descriptionTextArea.getText();
            Float price = (Float) priceTextField.getValue();
            Integer duration = (Integer) durationTextField.getValue();
            Date registryDate = new Date(dateTextField.getText());
            try{
                ic.updateActivityInfo(new DtActivity(name,description, duration, price, registryDate, -1, null));
                JOptionPane.showMessageDialog(this, "La actividad fue actualizada con éxito!",
                        "Modificar información de actividad", JOptionPane.INFORMATION_MESSAGE);
                restartUseCase();
            } catch (Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Modificar información de actividad", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void restartUseCase(){
            descriptionTextArea.setEnabled(false);
            descriptionTextArea.setText("");
            priceTextField.setEnabled(false);
            priceTextField.setValue(0.0);
            durationTextField.setEnabled(false);
            durationTextField.setValue(0);
            dateTextField.setText("");
            saveButton.setEnabled(false);
            cancelButton.setEnabled(false);
            namesComboBox.setSelectedIndex(0);
        }
        private void initialize(){
            JLabel title = new JLabel("Modificar actividad deportiva");
            title.setFont(new Font("Tahoma", Font.PLAIN, 20));
            title.setBounds(236, 10, 281, 40);

            JLabel descriptionLabel = new JLabel("Descripción");
            descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            descriptionLabel.setBounds(393, 132, 196, 26);
            descriptionLabel.setLabelFor(descriptionTextArea);

            descriptionTextArea.setEnabled(false);
            descriptionTextArea.setBounds(393, 163, 270, 204);
            descriptionTextArea.setLineWrap(true);
            descriptionTextArea.setWrapStyleWord(true);

            priceTextField.setEnabled(false);
            priceTextField.setBounds(103, 348, 183, 19);

            JLabel priceLabel = new JLabel("Precio");
            priceLabel.setLabelFor(priceTextField);
            priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            priceLabel.setBounds(103, 318, 139, 26);


            cancelButton.setEnabled(false);
            cancelButton.setBounds(366, 437, 85, 33);
            cancelButton.addActionListener(e -> {
                restartUseCase();
            });


            saveButton.setEnabled(false);
            saveButton.setBounds(201, 437, 85, 33);
            saveButton.addActionListener(e -> {
                updateActivityInfo();
            });

            JLabel namesLabel = new JLabel("Seleccione la actividad >");
            namesLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            namesLabel.setBounds(133, 60, 166, 26);

            namesLabel.setLabelFor(namesComboBox);
            namesComboBox.setBounds(366, 60, 223, 21);
            namesComboBox.addItemListener(e -> {
                String name = String.valueOf(namesComboBox.getSelectedItem());
                if(name.equals("<Nombres>") && !dateTextField.getText().isEmpty()) {
                    restartUseCase();
                    return;
                }
                fetchSelectedActivityData(name);
            });

            JSeparator separator = new JSeparator();
            separator.setBounds(48, 96, 691, 2);

            JLabel dateLabel = new JLabel("Fecha de registro ( dd/mm/aa )");
            dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            dateLabel.setBounds(103, 132, 196, 26);

            durationTextField.setEnabled(false);
            durationTextField.setBounds(103, 254, 183, 19);

            dateLabel.setLabelFor(dateTextField);
            dateTextField.setEditable(false);
            dateTextField.setEnabled(false);
            dateTextField.setBounds(103, 166, 183, 19);
            dateTextField.setColumns(10);

            JLabel durationLabel = new JLabel("Duración (en minutos)");
            durationLabel.setLabelFor(durationTextField);
            durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
            durationLabel.setBounds(103, 224, 139, 26);

            add(descriptionLabel);
            add(priceTextField);
            add(priceLabel);
            add(saveButton);
            add(cancelButton);
            add(descriptionTextArea);
            add(title);
            add(namesLabel);
            add(namesComboBox);
            add(separator);
            add(dateLabel);
            add(durationTextField);
            add(dateTextField);
            add(durationLabel);
        }
}

