/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moppydesk.ui;

import moppydesk.inputs.MoppySequencer;
import moppydesk.outputs.MoppyMIDIOutput;
import moppydesk.outputs.MoppyCOMBridge;
import moppydesk.outputs.MoppyPlayerOutput;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JList;
import javax.swing.JOptionPane;
import moppydesk.*;
import moppydesk.outputs.MoppyReceiver;

/**
 *
 * @author Sam
 */
public class MoppyControlWindow extends javax.swing.JFrame {

    MoppyUI app;
    HashMap<String, Info> availableMIDIOuts;
    OutputSetting[] outputSettings = new OutputSetting[Constants.NUM_MIDI_CHANNELS];
    
    HashMap<String,MoppyReceiver> outputPlayers = new HashMap<String, MoppyReceiver>();
    
    
    MIDIInControls midiInControls;
    SequencerControls seqControls;
    
    
    //PlayList playList = new PlayList();
    
    InputPanel currentInputPanel;

    /**
     * Creates new form MoppyControlWindow
     */
    public MoppyControlWindow(MoppyUI app) {
        this.app = app;
        
        midiInControls = new MIDIInControls(app.midiIn);
        seqControls = new SequencerControls(app, this, app.ms);
       
        app.ms.addListener(seqControls);
        
        availableMIDIOuts = MoppyMIDIOutput.getMIDIOutInfos();
        loadOutputSettings();

        initComponents();

        updateInputPanel();
        setupOutputControls();
    }

    private void loadOutputSettings() {
        OutputSetting[] os = (OutputSetting[]) app.getPreferenceObject(Constants.PREF_OUTPUT_SETTINGS);
        if (os == null) {
            for (int i = 1; i <= 16; i++) {
                outputSettings[i - 1] = new OutputSetting(i);
            }
            app.putPreferenceObject(Constants.PREF_OUTPUT_SETTINGS, outputSettings);
        } else {
            outputSettings = os;
        }
    }

    private void setupOutputControls() {

        for (OutputSetting s : outputSettings) {
            ChannelOutControl newControl = new ChannelOutControl(this, s);
            //TODO Read in preferences here?  Serialize all properties to preferences?
            mainOutputPanel.add(newControl);
        }
        mainOutputPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        mainStatusLabel = new javax.swing.JLabel();
        mainInputPanel = new javax.swing.JPanel();
        inputSelectBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        mainOutputPanel = new javax.swing.JPanel();
        connectButton = new javax.swing.JButton();
        poolingControls1 = new PoolingControls(app);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Moppy Control Application");

        mainStatusLabel.setText("Loaded.");
        mainStatusLabel.setToolTipText("Current status");

        mainInputPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainInputPanel.setPreferredSize(new java.awt.Dimension(350, 400));

        inputSelectBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MIDI File", "MIDI IN Port" }));
        inputSelectBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSelectBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Input Mode");

        mainOutputPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainOutputPanel.setPreferredSize(new java.awt.Dimension(350, 400));
        mainOutputPanel.setLayout(new javax.swing.BoxLayout(mainOutputPanel, javax.swing.BoxLayout.Y_AXIS));

        connectButton.setText("Connect");
        connectButton.setToolTipText("Saves current output settings and connects as specified.");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(493, 493, 493)
                                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mainInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                                    .addComponent(poolingControls1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainOutputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mainStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainOutputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mainInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poolingControls1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(connectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainStatusLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connect() {
        try {
            
            //Disable and save output settings...
            for (Component c : mainOutputPanel.getComponents()){
                if (c instanceof ChannelOutControl){
                    ((ChannelOutControl)c).lockControl();
                }
            }
            app.putPreferenceObject(Constants.PREF_OUTPUT_SETTINGS, outputSettings);
            app.savePreferences();
            
            setStatus("Initializing Receivers...");
            initializeReceivers();
            
            //If pooling is enabled, send messages through pooler, otherwise bypass it
            inputSelectBox.setEnabled(false);
            if (poolingControls1.isPoolingEnabled()){
                currentInputPanel.getTransmitter().setReceiver(poolingControls1.getDrivePooler());
                poolingControls1.getDrivePooler().setReceiver(app.rm);
            } else {
                currentInputPanel.getTransmitter().setReceiver(app.rm);
            }
            
            //Let the control pannels know they're connected.
            currentInputPanel.connected();
            poolingControls1.connected();
            
            connectButton.setText("Disconnect");
            setStatus("Connected.");
        } catch (Exception ex) {
            Logger.getLogger(MoppyControlWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, ex.toString(),ex.getMessage(),JOptionPane.ERROR_MESSAGE);
            disconnect();
        }
    }

    private void disconnect() {
        setStatus("Disconnecting...");
        currentInputPanel.disconnected();
        poolingControls1.disconnected();
        app.rm.close();
        
        //Reenable output settings
        for (Component c : mainOutputPanel.getComponents()){
                if (c instanceof ChannelOutControl){
                    ((ChannelOutControl)c).unlockControl();
                }
            }
        
        inputSelectBox.setEnabled(true);
        connectButton.setText("Connect");
        setStatus("Disconnected.");
    }

    private void initializeReceivers() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException, MidiUnavailableException {
        app.rm.clearReceivers();
        
        outputPlayers.clear();
        
        for (int ch = 1; ch <= 16; ch++) {
            OutputSetting os = outputSettings[ch-1]; //OutputSettings are 0-indexed
            if (os.enabled) {
                // MoppyPlayer/Receivers are grouped by COM port
                if (os.type.equals(OutputSetting.OutputType.MOPPY)) {
                    if (!outputPlayers.containsKey(os.comPort)){
                        outputPlayers.put(os.comPort, new MoppyPlayerOutput(new MoppyCOMBridge(os.comPort)));
                    }
                    app.rm.setReceiver(ch, outputPlayers.get(os.comPort));
                } 
                //MIDIPlayer/Receivers are grouped by MIDI output name
                else if (os.type.equals(OutputSetting.OutputType.MIDI)) {
                    if (!outputPlayers.containsKey(os.midiDeviceName)){
                        outputPlayers.put(os.midiDeviceName, new MoppyMIDIOutput(os.midiDeviceName));
                    }
                    app.rm.setReceiver(ch, outputPlayers.get(os.midiDeviceName));
                }
            }
        }
    }

    private void updateInputPanel(){
        
        mainInputPanel.removeAll();
        
        if (inputSelectBox.getSelectedIndex() == 0){ //MIDI File
            currentInputPanel = seqControls;
        } else { //MIDI IN
            currentInputPanel = midiInControls;
        }
        
        // Add the play list ListBox
        //mainInputPanel.add(playList);
        
        mainInputPanel.add(currentInputPanel);

        mainInputPanel.revalidate();
        mainInputPanel.repaint();
    }
    
    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        connectButton.setEnabled(false);
        if (connectButton.getText().equals("Connect")) {
            connect();
        } else {
            disconnect();
        }
        connectButton.setEnabled(true);
    }//GEN-LAST:event_connectButtonActionPerformed

    private void inputSelectBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSelectBoxActionPerformed
        updateInputPanel();
    }//GEN-LAST:event_inputSelectBoxActionPerformed

    public void setStatus(String newStatus) {
        mainStatusLabel.setText(newStatus);
        mainStatusLabel.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JComboBox inputSelectBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainInputPanel;
    private javax.swing.JPanel mainOutputPanel;
    private javax.swing.JLabel mainStatusLabel;
    private moppydesk.ui.PoolingControls poolingControls1;
    // End of variables declaration//GEN-END:variables
}
