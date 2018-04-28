package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
//реализует интерфейс для работы со стилем текста
public class StrikeThroughAction extends StyledEditorKit.StyledTextAction {
//вызывает родительский конструктор со стилем перечеркнутый текст
    public StrikeThroughAction() {
        super(StyleConstants.StrikeThrough.toString());
    }

    public void actionPerformed(ActionEvent actionEvent) {
//возвращает компонент с которым производится действие
        JEditorPane editor = getEditor(actionEvent);
//        если какое-либо действие было произведено, то
        if (editor != null) {
//            интерфейс для изменяемых атрибутов, получает атрибуты от компонента
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
//            простая реализация с помощью хеш таблиц
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
//            конструктор перечеркнутого стиля, boolean если атрибуты компонента перечеркнуты
            StyleConstants.setStrikeThrough(simpleAttributeSet, !StyleConstants.isStrikeThrough(mutableAttributeSet));
//            применяет полученые атрибуты
            setCharacterAttributes(editor, simpleAttributeSet, false);
        }
    }
}
