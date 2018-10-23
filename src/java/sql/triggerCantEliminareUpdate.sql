DELIMITER ||

drop trigger cantEliminareUpdate ||
CREATE TRIGGER cantEliminareUpdate
AFTER Update ON canteliminare 
FOR EACH ROW

BEGIN
    DECLARE wcantN double;
    DECLARE wcantO double;
    declare widMiscLuna integer;
    declare wagentN varchar(255);
    declare wagentO varchar(255);

    set wagentN ="";
    set wagentO ="";
    set wcantN=0.0;
    set wcantO=0.0;
    set widMiscLuna=0;
    set wcantN= new.cantitate;
    set wcantO= old.cantitate;
    set widMiscLuna = new.MiscariLuna_idMiscariLuna;
    set wagentN= new.agentEc;
    set wagentO= old.agentEc;

   
   update  miscariluna    set CantEliminate= CantEliminate-wcantO+wcantN where idMiscariLuna=widMiscLuna;
   update  miscariluna    set AgentElconomic=replace(AgentElconomic,wagentO,wagentN) where idMiscariLuna=widMiscLuna;
   update  miscariluna    set stocSfirsit= stocInceput+CantRezultata+miscGenerare  -miscEliminare-miscValorificare-miscStocare-ComponenteComercializate-CantReciclate-CantValorificate-CantEliminate  where idMiscariLuna=widMiscLuna;

END; ||

DELIMITER  ;
