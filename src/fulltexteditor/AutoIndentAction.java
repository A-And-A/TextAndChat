/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fulltexteditor;

/**
 *
 * @author archit08jain
 */
import javax.swing.*; 
import javax.swing.text.Document; 
import javax.swing.text.BadLocationException; 
import java.awt.event.ActionEvent; 
 

public class AutoIndentAction extends AbstractAction { 
    public void actionPerformed(ActionEvent ae) { 
        JTextArea comp = (JTextArea)ae.getSource(); 
        Document doc = comp.getDocument(); 
 
        if(!comp.isEditable()) 
            return; 
        try { 
            int line = comp.getLineOfOffset(comp.getCaretPosition()); 
 
            int start = comp.getLineStartOffset(line); 
            int end = comp.getLineEndOffset(line); 
            String str = doc.getText(start, end - start - 1); 
            String whiteSpace = getLeadingWhiteSpace(str); 
            doc.insertString(comp.getCaretPosition(), '\n' + whiteSpace, null); 
        } catch(BadLocationException ex) { 
            try { 
                doc.insertString(comp.getCaretPosition(), "\n", null); 
            } catch(BadLocationException ignore) { 
                // ignore 
            } 
        } 
    } 
 
    /** 
     *  Returns leading white space characters in the specified string. 
     */ 
    private String getLeadingWhiteSpace(String str) { 
        return str.substring(0, getLeadingWhiteSpaceWidth(str)); 
    } 
 
    /** 
     *  Returns the number of leading white space characters in the specified string. 
     */ 
    private int getLeadingWhiteSpaceWidth(String str) { 
        int whitespace = 0; 
        while(whitespace<str.length()) { 
            char ch = str.charAt(whitespace); 
            if(ch==' ' || ch=='\t') 
                whitespace++; 
            else 
                break; 
        } 
        return whitespace; 
    } 
} 
