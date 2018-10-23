DELIMITER ||

drop trigger miscariValorificareInsert ||
CREATE TRIGGER miscariValorificareInsert
AFTER INSERT ON miscarivalorificare
FOR EACH ROW

BEGIN
    DECLARE wcant double;
    declare widMiscLuna integer;
    declare wagent varchar(255);
    

    set wcant=0.0;
    set wagent ="";
    set widMiscLuna=0;
    set wcant= new.cantitate;
    set widMiscLuna = new.MiscariLuna_idMiscariLuna;
    set wagent= new.adentEc;

   
   update  miscariluna    set miscValorificare= miscValorificare+wcant where idMiscariLuna=widMiscLuna;
   update  miscariluna    set AgentElconomic=concat(AgentElconomic,',',wagent) where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;