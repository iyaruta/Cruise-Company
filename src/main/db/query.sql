SELECT tc.*, count(utt.ticket_id) as sold FROM TICKET_CLASS tc
INNER JOIN Ticket t ON t.tecket_class_id = tc.id
INNER JOIN Cruise c ON t.cruise_id = c.id
LEFT JOIN USER_TO_TICKET utt ON t.id = utt.ticket_id
WHERE c.id = ?
GROUP BY tc.id