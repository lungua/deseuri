DELIMITER ||

drop trigger cantReciclataInsert ||
CREATE TRIGGER cantReciclataInsert
AFTER INSERT ON cantreciclata
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
    set wagent= new.agentEc;

   
   update  miscariluna    set CantReciclate= CantReciclate+wcant where idMiscariLuna=widMiscLuna;
   update  miscariluna    set AgentElconomic=concat(AgentElconomic,',',wagent) where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;